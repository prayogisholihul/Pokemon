package com.zogik.core.data.my_pokemon.data_source.local

import com.zogik.core.data.detail.data_source.local.entity.MyPokemonEntity
import com.zogik.core.data.my_pokemon.data_source.local.dao.MyPokemonDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyPokemonLocalDataSourceImpl @Inject constructor(private val myPokemonDao: MyPokemonDao) :
    MyPokemonLocalDataSource {
    override fun getMyPokemonList(): Flow<List<MyPokemonEntity>> {
        return myPokemonDao.getMyPokemonList()
    }

    override fun deletePokemon(name: String) {
        myPokemonDao.deletePokemon(name)
    }
}
