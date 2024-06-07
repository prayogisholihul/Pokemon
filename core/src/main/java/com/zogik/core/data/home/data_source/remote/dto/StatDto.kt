package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Stat
import com.google.gson.annotations.SerializedName

data class StatDto(

    @SerializedName("base_stat")
    val baseStat: Int = 0,

    @SerializedName("effort")
    val effort: Int = 0,

    @SerializedName("stat")
    val stat: PokemonDto = PokemonDto()
)

fun StatDto.toModel() = Stat(
    this.baseStat,
    this.effort,
    this.stat.toModel()
)
