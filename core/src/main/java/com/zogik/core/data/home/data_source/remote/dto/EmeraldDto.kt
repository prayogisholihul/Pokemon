package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Emerald
import com.google.gson.annotations.SerializedName

data class EmeraldDto(

    @SerializedName("front_default")
    val frontDefault: String = "",

    @SerializedName("front_shiny")
    val frontShiny: String = ""
)

fun EmeraldDto.toModel() = Emerald(
    this.frontDefault,
    this.frontShiny
)
