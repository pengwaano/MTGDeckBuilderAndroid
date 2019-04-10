package com.jacobgreenland.mtgvisualdeckbuilder.utils

import io.realm.RealmList

object DataUtils {
    fun convertList(list: List<String>?): RealmList<String> {
        val newList = RealmList<String>()
        if (list != null) {
            for (text in list) {
                newList.add(text)
            }
        }
        return newList
    }
}