package com.zogik.core.data.home.data_source.remote

import com.zogik.core.data.home.data_source.remote.dto.PokemonDto
import com.zogik.core.domain.model.SpecificPokemon

interface HomeRemoteDataSource {
    suspend fun getPokemonList(offset: Int): List<PokemonDto>
    suspend fun getSpecificPokemon(name: String): SpecificPokemon
}
