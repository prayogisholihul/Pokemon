package com.zogik.core.util

sealed class ScreenRoute(val route: String) {
    data object Home : ScreenRoute("home_screen")
    data object Detail : ScreenRoute("detail_screen")
    data object MyPokemon : ScreenRoute("my_pokemon_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
