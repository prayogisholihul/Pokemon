package com.zogik.core.util

import java.util.Locale

object Extension {
    fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { str ->
            str.lowercase(Locale.ROOT)
                .replaceFirstChar {
                    if (it.isLowerCase()) {
                        it.titlecase(Locale.ROOT)
                    } else {
                        it.toString()
                    }
                }
        }
}
