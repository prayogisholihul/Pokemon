package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonDto(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("url")
    val url: String = ""
)

fun PokemonDto.toModel() = Pokemon(
    this.name,
    this.url
)
