package com.zogik.core.domain.model

import com.zogik.core.domain.model.Pokemon

data class Stat(
    val baseStat: Int = 0,
    val effort: Int = 0,
    val stat: Pokemon = Pokemon()
)
