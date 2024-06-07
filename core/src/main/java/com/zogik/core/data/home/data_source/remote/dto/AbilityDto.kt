package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.Ability
import com.google.gson.annotations.SerializedName

data class AbilityDto(

    @SerializedName("ability")
    val ability: PokemonDto = PokemonDto(),

    @SerializedName("is_hidden")
    val isHidden: Boolean = false,

    @SerializedName("slot")
    val slot: Int = 0
)

fun AbilityDto.toModel() = Ability(
    this.ability.toModel(),
    this.isHidden,
    this.slot
)
