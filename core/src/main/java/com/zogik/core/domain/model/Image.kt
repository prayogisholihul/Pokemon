package com.zogik.core.domain.model

data class Image(
    val back_default: String = "",
    val back_female: Any? = null,
    val back_shiny: String = "",
    val back_shiny_female: Any? = null,
    val front_default: String = "",
    val front_female: Any? = null,
    val front_shiny: String = "",
    val front_shiny_female: Any? = null
)
