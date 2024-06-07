package com.zogik.core.domain.my_pokemon.repository

import com.zogik.core.domain.model.MyPokemon
import kotlinx.coroutines.flow.Flow

interface MyPokemonRepository {
    fun getMyPokemonList(): Flow<List<MyPokemon>>
    fun deletePokemon(name: String)
}
