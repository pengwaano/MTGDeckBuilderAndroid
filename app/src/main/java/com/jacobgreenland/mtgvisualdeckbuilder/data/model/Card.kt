package com.jacobgreenland.mtgvisualdeckbuilder.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Card(
    @PrimaryKey var id: String = "",
    var name: String = "",
    var names: RealmList<String>? = null,
    var manaCost: String? = null,
    var cmc: Int? = null,
    var colors: RealmList<String>? = null,
    var colorIdentity: RealmList<String>? = null,
    var type: String = "",
    var supertypes: RealmList<String>? = null,
    var types: RealmList<String> = RealmList(),
    var subtypes: RealmList<String>? = null,
    var rarity: String = "",
    var set: String = "",
    var setName: String = "",
    var text: String? = null,
    var artist: String = "",
    var number: String? = null,
    var power: String? = null,
    var toughness: String? = null,
    var loyalty: Int? = null,
    var multiverseid: Int? = null,
    var imageUrl: String? = null,
    var layout: String = ""
) : RealmObject() {

}