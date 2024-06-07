package com.zogik.core.data.home.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import com.zogik.core.data.home.data_source.remote.dto.PokemonDto

data class PokemonResponse(

    @SerializedName("count")
    val count: Int = 0,

    @SerializedName("next")
    val next: String = "",

    @SerializedName("previous")
    val previous: Any? = null,

    @SerializedName("results")
    val results: List<PokemonDto> = listOf()
)
