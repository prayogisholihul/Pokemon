package com.zogik.core.data.detail.data_source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.zogik.core.domain.model.MyPokemon

@Entity(tableName = "Pokemon")
data class MyPokemonEntity(
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "img_url")
    var imgUrl: String = "",

    @ColumnInfo(name = "owned")
    var owned: Int = 0,
)

fun MyPokemonEntity.toModel() = MyPokemon(
    this.id,
    this.name,
    this.imgUrl,
    this.owned
)
