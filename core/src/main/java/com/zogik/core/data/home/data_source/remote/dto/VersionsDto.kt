package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Versions
import com.google.gson.annotations.SerializedName

data class VersionsDto(

    @SerializedName("generation-i")
    val generationI: GenerationIDto = GenerationIDto(),

    @SerializedName("generation-ii")
    val generationII: GenerationIIDto = GenerationIIDto(),

    @SerializedName("generation-iii")
    val generationIII: GenerationIIIDto = GenerationIIIDto(),

    @SerializedName("generation-iv")
    val generationIV: GenerationIVDto = GenerationIVDto(),

    @SerializedName("generation-v")
    val generationV: GenerationVDto = GenerationVDto(),

    @SerializedName("generation-vi")
    val generationVI: GenerationVIDto = GenerationVIDto(),

    @SerializedName("generation-vii")
    val generationVII: GenerationVIIDto = GenerationVIIDto(),

    @SerializedName("generation-viii")
    val generationVIII: GenerationVIIIDto = GenerationVIIIDto()
)

fun VersionsDto.toModel() = Versions(
    this.generationI.toModel(),
    this.generationII.toModel(),
    this.generationIII.toModel(),
    this.generationIV.toModel(),
    this.generationV.toModel(),
    this.generationVI.toModel(),
    this.generationVII.toModel(),
    this.generationVIII.toModel()
)
