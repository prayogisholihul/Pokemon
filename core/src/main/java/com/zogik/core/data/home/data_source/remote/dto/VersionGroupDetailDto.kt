package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.VersionGroupDetail
import com.google.gson.annotations.SerializedName

data class VersionGroupDetailDto(

    @SerializedName("level_learned_at")
    val levelLearnedAt: Int = 0,

    @SerializedName("move_learn_method")
    val moveLearnMethod: PokemonDto = PokemonDto(),

    @SerializedName("version_group")
    val versionGroup: PokemonDto = PokemonDto()
)

fun VersionGroupDetailDto.toModel() = VersionGroupDetail(
    this.levelLearnedAt,
    this.moveLearnMethod.toModel(),
    this.versionGroup.toModel()
)
