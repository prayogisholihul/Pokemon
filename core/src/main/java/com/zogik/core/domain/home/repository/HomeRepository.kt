package com.zogik.core.domain.home.repository

import com.zogik.core.domain.model.Pokemon
import com.zogik.core.domain.model.SpecificPokemon

interface HomeRepository {
    suspend fun getPokemonList(offset: Int): List<Pokemon>
    suspend fun getSpecificPokemon(name: String): SpecificPokemon
}
