package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Other
import com.google.gson.annotations.SerializedName

data class OtherDto(

    @SerializedName("dream_world")
    val dreamWorld: IconsDto = IconsDto(),

    @SerializedName("home")
    val home: XYDto = XYDto(),

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto = OfficialArtworkDto()
)

fun OtherDto.toModel() = Other(
    this.dreamWorld.toModel(),
    this.home.toModel(),
    this.officialArtwork.toModel()
)
