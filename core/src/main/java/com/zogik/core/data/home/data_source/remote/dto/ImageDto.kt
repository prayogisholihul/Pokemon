package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageDto(

    @SerializedName("back_default")
    val back_default: String = "",

    @SerializedName("back_female")
    val back_female: Any? = null,

    @SerializedName("back_shiny")
    val back_shiny: String = "",

    @SerializedName("back_shiny_female")
    val back_shiny_female: Any? = null,

    @SerializedName("front_default")
    val front_default: String = "",

    @SerializedName("front_female")
    val front_female: Any? = null,

    @SerializedName("front_shiny")
    val front_shiny: String = "",

    @SerializedName("front_shiny_female")
    val front_shiny_female: Any? = null
)

fun ImageDto.toModel() = Image(
    this.back_default,
    this.back_female,
    this.back_shiny,
    this.back_shiny_female,
    this.front_default,
    this.front_female,
    this.front_shiny,
    this.front_shiny_female,
)
