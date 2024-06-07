package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.RubySapphire
import com.google.gson.annotations.SerializedName

data class RubySapphireDto(
    @SerializedName("back_default")
    val backDefault: String = "",

    @SerializedName("back_shiny")
    val backShiny: String = "",

    @SerializedName("front_default")
    val frontDefault: String = "",

    @SerializedName("front_shiny")
    val frontShiny: String = ""
)

fun RubySapphireDto.toModel() = RubySapphire(
    this.backDefault,
    this.backShiny,
    this.frontDefault,
    this.frontShiny
)
