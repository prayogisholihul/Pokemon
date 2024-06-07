package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.XY
import com.google.gson.annotations.SerializedName

data class XYDto(

    @SerializedName("front_default")
    val frontDefault: String = "",

    @SerializedName("front_female")
    val frontFemale: Any? = null,

    @SerializedName("front_shiny")
    val frontShiny: String = "",

    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any? = null
)

fun XYDto.toModel() = XY(
    this.frontDefault,
    this.frontFemale,
    this.frontShiny,
    this.frontShinyFemale
)
