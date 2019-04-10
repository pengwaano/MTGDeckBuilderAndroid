package com.jacobgreenland.tcghub_pokemon.di.components

import com.jacobgreenland.mtgvisualdeckbuilder.MainActivity
import com.jacobgreenland.tcghub_pokemon.di.modules.DatabaseModules

import dagger.Component

@Component(modules = [DatabaseModules::class])
interface DatabaseComponent {
    fun inject(mainActivity: MainActivity)
}