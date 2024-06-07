package com.zogik.core.domain.model

import com.zogik.core.data.detail.data_source.local.entity.MyPokemonEntity

data class MyPokemon(
    override val id: Int = 9999,
    val name: String = "",
    val imgUrl: String = "",
    val owned: Int = 0,
) : LazyGridData

fun MyPokemon.toEntity() = MyPokemonEntity(
    this.id,
    this.name,
    this.imgUrl,
    this.owned
)
