package com.zogik.core.data.home.repository

import com.zogik.core.data.home.data_source.remote.HomeRemoteDataSource
import com.zogik.core.data.home.data_source.remote.dto.toModel
import com.zogik.core.domain.model.Pokemon
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.domain.home.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    override suspend fun getPokemonList(offset: Int): List<Pokemon> {
        return homeRemoteDataSource.getPokemonList(offset).map { it.toModel() }
    }

    override suspend fun getSpecificPokemon(name: String): SpecificPokemon {
        return homeRemoteDataSource.getSpecificPokemon(name)
    }
}
