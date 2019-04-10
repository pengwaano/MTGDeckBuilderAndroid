package com.jacobgreenland.mtgvisualdeckbuilder.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Set(
    @PrimaryKey var code: String = "",
    var name: String = "",
    var type: String = "",
    var releaseDate: Date = Date(),
    var block: String? = null
) : RealmObject() {


}

