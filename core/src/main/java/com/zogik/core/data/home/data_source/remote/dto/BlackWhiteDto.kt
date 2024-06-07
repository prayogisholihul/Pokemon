package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.BlackWhite
import com.google.gson.annotations.SerializedName

data class BlackWhiteDto(

    @SerializedName("animated")
    val animated: ImageDto = ImageDto(),

    @SerializedName("back_default")
    val backDefault: String = "",

    @SerializedName("back_female")
    val backFemale: Any? = null,

    @SerializedName("back_shiny")
    val backShiny: String = "",

    @SerializedName("back_shiny_female")
    val backShinyFemale: Any? = null,

    @SerializedName("front_default")
    val frontDefault: String = "",

    @SerializedName("front_female")
    val frontFemale: Any? = null,

    @SerializedName("front_shiny")
    val frontShiny: String = "",

    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any? = null
)

fun BlackWhiteDto.toModel() = BlackWhite(
    this.animated.toModel(),
    this.backDefault,
    this.backFemale,
    this.backShiny,
    this.backShinyFemale,
    this.frontDefault,
    this.frontFemale,
    this.frontShiny,
    this.frontShinyFemale,
)
