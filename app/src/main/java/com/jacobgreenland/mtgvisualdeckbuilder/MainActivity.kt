package com.jacobgreenland.mtgvisualdeckbuilder

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jacobgreenland.mtgvisualdeckbuilder.data.MagicApi
import com.jacobgreenland.mtgvisualdeckbuilder.data.ModelMapper
import com.jacobgreenland.mtgvisualdeckbuilder.data.RealmDatabase
import com.jacobgreenland.mtgvisualdeckbuilder.data.model.Card
import com.jacobgreenland.mtgvisualdeckbuilder.data.model.Set
import com.jacobgreenland.tcghub_pokemon.di.components.DaggerDatabaseComponent
import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var database: RealmDatabase

    var setLoopCount = 0

    var completedSets = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerDatabaseComponent.create().inject(this)

//        prepopDatabase()

        var cards = database.r().where(Card::class.java).findAll()

        Log.d("Count", "Cards: ${cards.size}")
    }

    fun prepopDatabase() {
        MagicApi().getAllSets(database.r())

        var sets = database.r().where(Set::class.java).findAll()

        getCards(sets)
    }

    fun getCards(sets: List<Set>) {
        if(sets.isEmpty())
            return
        if(setLoopCount > 5)
            return
        Observable.fromIterable(sets)
            .flatMap({
                Log.d("Set", "Current set: ${it.code}")
                MtgCardApiClient.getAllCardsBySetCodeObservable(it.code, pageSize = 100, page = setLoopCount)
                    .subscribeOn(Schedulers.io())
            }, true, 2)
            .filter { it.isNotEmpty() }
            .map { ModelMapper.toCards(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ cards ->
                database.r().executeTransactionAsync { it.insertOrUpdate(cards) }
            }, {
                Log.e("Set", it.message)
//                getCards(sets)
                setLoopCount++
                getCards(sets)
            }, {
                Log.d("Set", "Complete")
                setLoopCount++
                getCards(sets)
            })
    }
}
