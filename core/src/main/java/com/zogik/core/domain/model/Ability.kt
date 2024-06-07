package com.zogik.core.domain.model

data class Ability(
    val ability: Pokemon = Pokemon(),
    val isHidden: Boolean = false,
    val slot: Int = 0
)
