package com.zogik.core.data.my_pokemon.data_source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.zogik.core.data.detail.data_source.local.entity.MyPokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPokemonDao {
    @Query("SELECT * FROM Pokemon")
    fun getMyPokemonList(): Flow<List<MyPokemonEntity>>

    @Query("DELETE FROM Pokemon WHERE name = :name")
    fun deletePokemon(name: String)
}
