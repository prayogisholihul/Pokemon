package com.zogik.pokemon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zogik.feature.detail.screen.DetailScreen
import com.zogik.feature.my_pokemon.screen.MyPokemonScreen
import com.zogik.core.util.ScreenRoute
import com.zogik.feature.home.screen.HomeScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = ScreenRoute.Home.route) {
        composable(route = ScreenRoute.Home.route) {
            HomeScreen(navHostController)
        }
        composable(
            route = ScreenRoute.Detail.route + "/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            val name = it.arguments?.getString("name")
            DetailScreen(navHostController, name.orEmpty())
        }
        composable(
            route = ScreenRoute.MyPokemon.route,
        ) {
            MyPokemonScreen(navHostController)
        }
    }
}
