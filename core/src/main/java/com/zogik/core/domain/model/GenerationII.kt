package com.zogik.core.domain.model

import com.google.gson.annotations.SerializedName

data class GenerationII(

    @SerializedName("crystal")
    val crystal: Crystal = Crystal(),

    @SerializedName("gold")
    val gold: SilverGold = SilverGold(),

    @SerializedName("silver")
    val silver: SilverGold = SilverGold()
)
