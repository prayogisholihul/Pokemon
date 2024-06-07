package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Type
import com.google.gson.annotations.SerializedName

data class TypeDto(

    @SerializedName("slot")
    val slot: Int = 0,

    @SerializedName("type")
    val type: PokemonDto = PokemonDto()
)

fun TypeDto.toModel() = Type(
    this.slot,
    this.type.toModel()
)
