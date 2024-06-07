package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.GenerationVI
import com.google.gson.annotations.SerializedName

data class GenerationVIDto(

    @SerializedName("omegaruby-alphasapphire")
    val omegaRubyAlphaSapphire: XYDto = XYDto(),

    @SerializedName("x-y")
    val xy: XYDto = XYDto()
)

fun GenerationVIDto.toModel() = GenerationVI(
    this.omegaRubyAlphaSapphire.toModel(),
    this.xy.toModel()
)
