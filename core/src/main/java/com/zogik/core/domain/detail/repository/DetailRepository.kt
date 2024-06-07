package com.zogik.core.domain.detail.repository

import com.zogik.core.domain.model.MyPokemon

interface DetailRepository {
    fun catchPokemon(pokemon: MyPokemon)
    fun updateOwnedValue(name: String)
    fun isPokemonExist(name: String): Boolean
}
