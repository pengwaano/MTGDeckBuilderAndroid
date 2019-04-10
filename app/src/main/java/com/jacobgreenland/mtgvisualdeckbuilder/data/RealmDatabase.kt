package com.jacobgreenland.mtgvisualdeckbuilder.data

import io.realm.Realm

class RealmDatabase {
    private var realm: Realm = Realm.getDefaultInstance()

    fun r(): Realm {
        return realm
    }
}