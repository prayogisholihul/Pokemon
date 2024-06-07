package com.zogik.core.data.detail.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zogik.core.data.detail.data_source.local.entity.MyPokemonEntity

@Dao
interface DetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun catchPokemon(pokemon: MyPokemonEntity)

    @Query("UPDATE Pokemon SET owned = owned + 1 WHERE name = :name")
    fun updateOwnedValue(name: String)

    @Query("SELECT EXISTS(SELECT * FROM Pokemon WHERE name = :name)")
    fun isPokemonExist(name: String): Boolean
}
