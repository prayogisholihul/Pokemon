package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.GenerationIV
import com.google.gson.annotations.SerializedName

data class GenerationIVDto(

    @SerializedName("diamond-pearl")
    val diamondPearl: ImageDto = ImageDto(),

    @SerializedName("heartgold-soulsilver")
    val heartGoldSoulSilver: ImageDto = ImageDto(),

    @SerializedName("platinum")
    val platinum: ImageDto = ImageDto()
)

fun GenerationIVDto.toModel() = GenerationIV(
    this.diamondPearl.toModel(),
    this.heartGoldSoulSilver.toModel(),
    this.platinum.toModel()
)
