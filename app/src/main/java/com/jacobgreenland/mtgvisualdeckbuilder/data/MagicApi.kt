package com.jacobgreenland.mtgvisualdeckbuilder.data

import android.annotation.SuppressLint
import android.util.Log
import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.api.MtgSetApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.set.MtgSet
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

@SuppressLint("CheckResult")
class MagicApi {

    fun getAllSets(realm: Realm) {
        val observable: Observable<List<MtgSet>> = MtgSetApiClient.getAllSetsObservable()
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ModelMapper.to(it) }
            .subscribe({ sets ->
                realm.executeTransactionAsync { it.insertOrUpdate(sets) }
            }, {
                Log.e("Error", it.message)
            })
    }

    fun getCardsForSet(realm: Realm, setCode: String) {
        getCards(realm, setCode, 0)
    }

    private fun getCards(realm: Realm, setCode: String, count: Int) {
        var innerCount = count
        val observable: Observable<List<MtgCard>> = MtgCardApiClient.getAllCardsBySetCodeObservable(setCode, pageSize = 100, page = innerCount)
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ModelMapper.toCards(it) }
            .subscribe({ cards ->
                realm.executeTransactionAsync { it.insertOrUpdate(cards) }
                if(cards.size == 100) {
                    innerCount++
                    getCards(realm, setCode, innerCount)
                }
            }, {
                Log.e("Error", it.message)
            })
    }
}