package com.zogik.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zogik.core.data.detail.data_source.local.dao.DetailDao
import com.zogik.core.data.detail.data_source.local.entity.MyPokemonEntity
import com.zogik.core.data.my_pokemon.data_source.local.dao.MyPokemonDao

@Database(entities = [MyPokemonEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun myPokemonDao(): MyPokemonDao
    abstract fun detailDao(): DetailDao
}
