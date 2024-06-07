package com.zogik.core.data.my_pokemon.repository

import com.zogik.core.data.detail.data_source.local.entity.toModel
import com.zogik.core.data.my_pokemon.data_source.local.MyPokemonLocalDataSource
import com.zogik.core.domain.model.MyPokemon
import com.zogik.core.domain.my_pokemon.repository.MyPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MyPokemonRepositoryImpl @Inject constructor(
    private val myPokemonLocalDataSource: MyPokemonLocalDataSource
) : MyPokemonRepository {
    override fun getMyPokemonList(): Flow<List<MyPokemon>> {
        return myPokemonLocalDataSource.getMyPokemonList()
            .map { pokemon -> pokemon.map { it.toModel() } }
    }

    override fun deletePokemon(name: String) {
        myPokemonLocalDataSource.deletePokemon(name)
    }
}
