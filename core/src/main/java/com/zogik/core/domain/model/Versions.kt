package com.zogik.core.domain.model

import com.zogik.core.domain.model.GenerationI
import com.zogik.core.domain.model.GenerationII
import com.zogik.core.domain.model.GenerationIII
import com.zogik.core.domain.model.GenerationIV
import com.zogik.core.domain.model.GenerationV
import com.zogik.core.domain.model.GenerationVI
import com.zogik.core.domain.model.GenerationVII
import com.zogik.core.domain.model.GenerationVIII

data class Versions(
    val generationI: GenerationI = GenerationI(),
    val generationII: GenerationII = GenerationII(),
    val generationIII: GenerationIII = GenerationIII(),
    val generationIV: GenerationIV = GenerationIV(),
    val generationV: GenerationV = GenerationV(),
    val generationVI: GenerationVI = GenerationVI(),
    val generationVII: GenerationVII = GenerationVII(),
    val generationVIII: GenerationVIII = GenerationVIII()
)
