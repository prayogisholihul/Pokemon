package com.zogik.core.domain.model

data class BlackWhite(
    val animated: Image = Image(),
    val backDefault: String = "",
    val backFemale: Any? = null,
    val backShiny: String = "",
    val backShinyFemale: Any? = null,
    val frontDefault: String = "",
    val frontFemale: Any? = null,
    val frontShiny: String = "",
    val frontShinyFemale: Any? = null
)
