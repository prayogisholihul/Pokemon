package com.zogik.core.data.detail.repository

import com.zogik.core.data.detail.data_source.local.DetailLocalDataSource
import com.zogik.core.domain.detail.repository.DetailRepository
import com.zogik.core.domain.model.MyPokemon
import com.zogik.core.domain.model.toEntity
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailLocalDataSource: DetailLocalDataSource
) : DetailRepository {
    override fun catchPokemon(pokemon: MyPokemon) {
        detailLocalDataSource.catchPokemon(pokemon.toEntity())
    }

    override fun updateOwnedValue(name: String) {
        detailLocalDataSource.updateOwnedValue(name)
    }

    override fun isPokemonExist(name: String): Boolean {
        return detailLocalDataSource.isPokemonExist(name)
    }
}
