package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.GameIndice
import com.google.gson.annotations.SerializedName

data class GameIndiceDto(

    @SerializedName("game_index")
    val gameIndex: Int = 0,

    @SerializedName("version")
    val version: PokemonDto = PokemonDto()
)

fun GameIndiceDto.toModel() = GameIndice(
    this.gameIndex,
    this.version.toModel()
)
