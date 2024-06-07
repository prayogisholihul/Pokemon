package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Sprites
import com.google.gson.annotations.SerializedName

data class SpritesDto(

    @SerializedName("back_default")
    val backDefault: String = "",

    @SerializedName("back_female")
    val backFemale: Any? = Any(),

    @SerializedName("back_shiny")
    val backShiny: String = "",

    @SerializedName("back_shiny_female")
    val backShinyFemale: Any? = Any(),

    @SerializedName("front_default")
    val frontDefault: String = "",

    @SerializedName("front_female")
    val frontFemale: Any? = Any(),

    @SerializedName("front_shiny")
    val frontShiny: String = "",

    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any? = Any(),

    @SerializedName("other")
    val other: OtherDto = OtherDto(),

    @SerializedName("versions")
    val versions: VersionsDto = VersionsDto()
)

fun SpritesDto.toModel() = Sprites(
    this.backDefault,
    this.backFemale,
    this.backShiny,
    this.backShinyFemale,
    this.frontDefault,
    this.frontFemale,
    this.frontShiny,
    this.frontShinyFemale,
    this.other.toModel(),
    this.versions.toModel(),
)
