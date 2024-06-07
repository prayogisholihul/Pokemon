package com.zogik.core.data.home.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import com.zogik.core.domain.model.GenerationVII

data class GenerationVIIDto(

    @SerializedName("icons")
    val icons: IconsDto = IconsDto(),

    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: XYDto = XYDto()
)

fun GenerationVIIDto.toModel() = GenerationVII(
    this.icons.toModel(),
    this.ultraSunUltraMoon.toModel()
)
