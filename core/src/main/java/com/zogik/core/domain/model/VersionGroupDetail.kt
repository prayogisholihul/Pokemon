package com.zogik.core.domain.model

import com.zogik.core.domain.model.Pokemon

data class VersionGroupDetail(
    val levelLearnedAt: Int = 0,
    val moveLearnMethod: Pokemon = Pokemon(),
    val versionGroup: Pokemon = Pokemon()
)
