package com.jacobgreenland.mtgvisualdeckbuilder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jacobgreenland.mtgvisualdeckbuilder.data.MagicApi
import com.jacobgreenland.mtgvisualdeckbuilder.data.RealmDatabase
import com.jacobgreenland.mtgvisualdeckbuilder.data.model.Set
import com.jacobgreenland.tcghub_pokemon.di.components.DaggerDatabaseComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var database: RealmDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerDatabaseComponent.create().inject(this)

        MagicApi().getAllSets(database.r())

        database.r().where(Set::class.java).findAll().forEach {
            MagicApi().getCardsForSet(database.r(), it.code)
        }
    }
}
