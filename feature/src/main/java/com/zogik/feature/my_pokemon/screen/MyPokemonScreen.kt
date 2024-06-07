package com.zogik.feature.my_pokemon.screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zogik.core.domain.model.LazyGridData
import com.zogik.core.domain.model.LoadingDataModel
import com.zogik.core.domain.model.MyPokemon
import com.zogik.core.theme.PokemonTheme
import com.zogik.core.util.Resource
import com.zogik.core.util.ScreenRoute
import com.zogik.feature.common.list_item.ItemEmpty
import com.zogik.feature.common.progress_indicator.CircularProgressBar
import com.zogik.feature.my_pokemon.component.app_bar.MyPokemonTopAppBar
import com.zogik.feature.my_pokemon.component.list_item.ItemMyPokemon
import com.zogik.feature.my_pokemon.viewmodel.MyPokemonViewModel

const val GRID_COLUMN_SIZE = 2

@Composable
fun MyPokemonScreen(
    navController: NavController,
    myPokemonViewModel: MyPokemonViewModel = hiltViewModel()
) {
    val myPokemonListState = myPokemonViewModel.myPokemonListState.collectAsState().value
    val myPokemonList = myPokemonListState.data
    val lazyGridState = rememberLazyGridState()

    Scaffold(
        topBar = {
            MyPokemonTopAppBar {
                navController.navigateUp()
            }
        },
        content = {
            when (myPokemonListState) {
                is Resource.Loading -> {
                    CircularProgressBar(modifier = Modifier.fillMaxSize())
                }

                is Resource.Empty -> {
                    ItemEmpty(modifier = Modifier.fillMaxSize())
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
                        )
                    ) {
                        items(
                            items = myPokemonList as List<LazyGridData>,
                            span = { item ->
                                val span = if (item is LoadingDataModel) {
                                    GRID_COLUMN_SIZE
                                } else {
                                    1
                                }
                                GridItemSpan(span)
                            },
                        ) { item ->
                            when (item) {
                                is LoadingDataModel -> {
                                    CircularProgressBar(
                                        modifier = Modifier.height(
                                            64.dp
                                        )
                                    )
                                }

                                is MyPokemon -> {
                                    ItemMyPokemon(
                                        data = MyPokemon(
                                            name = item.name,
                                            imgUrl = item.imgUrl,
                                            owned = item.owned
                                        ),
                                        onClick = {
                                            navController.navigate(
                                                ScreenRoute.Detail.withArgs(item.name)
                                            )
                                        },
                                        onDelete = { myPokemonViewModel.deletePokemon(item.name) }
                                    )
                                }
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun MyPokemonScreenPreview() {
    PokemonTheme {
        MyPokemonScreen(rememberNavController())
    }
}
