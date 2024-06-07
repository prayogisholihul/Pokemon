package com.zogik.core.data.detail.data_source.local

import com.zogik.core.data.detail.data_source.local.dao.DetailDao
import com.zogik.core.data.detail.data_source.local.entity.MyPokemonEntity
import javax.inject.Inject

class DetailLocalDataSourceImpl @Inject constructor(private val detailDao: DetailDao) :
    DetailLocalDataSource {
    override fun catchPokemon(pokemon: MyPokemonEntity) {
        detailDao.catchPokemon(pokemon)
    }

    override fun updateOwnedValue(name: String) {
        detailDao.updateOwnedValue(name)
    }

    override fun isPokemonExist(name: String): Boolean {
        return detailDao.isPokemonExist(name)
    }
}
