package com.jacobgreenland.mtgvisualdeckbuilder

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        var config = RealmConfiguration.Builder()
            .assetFile("default.realm")
            .name("default.realm")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}