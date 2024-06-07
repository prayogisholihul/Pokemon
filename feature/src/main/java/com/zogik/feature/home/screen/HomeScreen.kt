package com.zogik.feature.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zogik.core.domain.model.LazyGridData
import com.zogik.core.domain.model.LoadingDataModel
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.theme.PokemonTheme
import com.zogik.core.util.OnBottomReached
import com.zogik.core.util.Resource
import com.zogik.core.util.ScreenRoute
import com.zogik.feature.common.list_item.ItemError
import com.zogik.feature.common.progress_indicator.CircularProgressBar
import com.zogik.feature.home.component.app_bar.HomeTopAppBar
import com.zogik.feature.home.component.list_item.ItemPokemon
import com.zogik.feature.home.viewmodel.HomeViewModel

const val GRID_COLUMN_SIZE = 2

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val pokemonListState = homeViewModel.pokemonListState.collectAsState().value
    val lazyGridState = rememberLazyGridState()

    Scaffold(
        topBar = {
            HomeTopAppBar {
                navController.navigate(ScreenRoute.MyPokemon.route)
            }
        },
        content = {
            when (pokemonListState) {
                is Resource.Loading -> {
                    CircularProgressBar(modifier = Modifier.fillMaxSize())
                }

                is Resource.Success -> {
                    LazyVerticalGrid(
                        state = lazyGridState,
                        modifier = Modifier.padding(it).padding(16.dp),
                        columns = GridCells.Fixed(GRID_COLUMN_SIZE),
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp
                        ),
                    ) {
                        items(
                            items = pokemonListState.data as List<LazyGridData>,
                            span = { item ->
                                val span = if (item is LoadingDataModel) {
                                    GRID_COLUMN_SIZE
                                } else {
                                    1
                                }
                                GridItemSpan(span)
                            },
                            key = { item -> item.id }
                        ) { item ->
                            when (item) {
                                is LoadingDataModel -> {
                                    CircularProgressBar(
                                        modifier = Modifier.height(
                                            64.dp
                                        )
                                    )
                                }

                                is SpecificPokemon -> {
                                    ItemPokemon(data = item) {
                                        navController.navigate(
                                            ScreenRoute.Detail.withArgs(item.name)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    lazyGridState.OnBottomReached(buffer = 2) {
                        homeViewModel.loadNext()
                    }
                }

                is Resource.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ItemError(modifier = Modifier.fillMaxSize()) {
                            homeViewModel.getPokemonList()
                            homeViewModel.initLoading()
                        }
                    }
                }

                else -> {}
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PokemonTheme {
        HomeScreen(rememberNavController())
    }
}
