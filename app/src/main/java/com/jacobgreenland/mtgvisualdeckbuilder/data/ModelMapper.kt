package com.jacobgreenland.mtgvisualdeckbuilder.data

import com.jacobgreenland.mtgvisualdeckbuilder.data.model.Card
import com.jacobgreenland.mtgvisualdeckbuilder.data.model.Set
import com.jacobgreenland.mtgvisualdeckbuilder.utils.DataUtils.convertList
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.set.MtgSet

object ModelMapper {

    fun to(mtgSets: List<MtgSet>): List<Set> {
        val list = mutableListOf<Set>()
        for (mtgSet in mtgSets) {
            list.add(to(mtgSet))
        }
        return list
    }

    fun to(mtgSet: MtgSet): Set {
        return Set(mtgSet.code, mtgSet.name, mtgSet.type, mtgSet.releaseDate.toDate(), mtgSet.block)
    }

    fun toCards(mtgCards: List<MtgCard>): List<Card> {
        val list = mutableListOf<Card>()
        for (mtgSet in mtgCards) {
            list.add(to(mtgSet))
        }
        return list
    }

    fun to(mtgCard: MtgCard): Card {
        return Card(mtgCard.id!!, mtgCard.name, convertList(mtgCard.names), mtgCard.manaCost, mtgCard.cmc, convertList(mtgCard.colors), convertList(mtgCard.colorIdentity), mtgCard.type, convertList(mtgCard.supertypes), convertList(mtgCard.types), convertList(mtgCard.subtypes), mtgCard.rarity,
            mtgCard.set, mtgCard.setName, mtgCard.text, mtgCard.artist, mtgCard.number, mtgCard.power, mtgCard.toughness, mtgCard.loyalty, mtgCard.multiverseid, mtgCard.imageUrl, mtgCard.layout)
    }
}