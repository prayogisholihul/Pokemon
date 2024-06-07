package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Icons
import com.google.gson.annotations.SerializedName

data class IconsDto(

    @SerializedName("front_default")
    val frontDefault: String = "",

    @SerializedName("front_female")
    val frontFemale: Any? = null
)

fun IconsDto.toModel() = Icons(
    this.frontDefault,
    this.frontFemale
)
