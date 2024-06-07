package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.GenerationVIII
import com.google.gson.annotations.SerializedName

data class GenerationVIIIDto(
    @SerializedName("icons")
    val icons: IconsDto = IconsDto()
)

fun GenerationVIIIDto.toModel() = GenerationVIII(
    this.icons.toModel()
)
