package com.jacobgreenland.tcghub_pokemon.di.modules

import com.jacobgreenland.mtgvisualdeckbuilder.data.RealmDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModules {
    @Provides
    fun realmDatabase(): RealmDatabase {
        return RealmDatabase()
    }
}