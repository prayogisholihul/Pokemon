package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.GenerationV
import com.google.gson.annotations.SerializedName

data class GenerationVDto(
    @SerializedName("black-white")
    val blackWhite: BlackWhiteDto = BlackWhiteDto()
)

fun GenerationVDto.toModel() = GenerationV(
    this.blackWhite.toModel()
)
