package com.zogik.core.data.detail.data_source.local

import com.zogik.core.data.detail.data_source.local.entity.MyPokemonEntity

interface DetailLocalDataSource {
    fun catchPokemon(pokemon: MyPokemonEntity)
    fun updateOwnedValue(name: String)
    fun isPokemonExist(name: String): Boolean
}
