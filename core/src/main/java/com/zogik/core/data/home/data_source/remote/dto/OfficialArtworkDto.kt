package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.OfficialArtwork
import com.google.gson.annotations.SerializedName

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String = ""
)

fun OfficialArtworkDto.toModel() =
    OfficialArtwork(
        this.frontDefault
    )
