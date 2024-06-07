package com.zogik.feature.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zogik.core.domain.model.MyPokemon
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.theme.PokemonTheme
import com.zogik.core.util.Constant.ATK
import com.zogik.core.util.Constant.DEF
import com.zogik.core.util.Constant.SPEED
import com.zogik.core.util.Constant.SP_ATK
import com.zogik.core.util.Constant.SP_DEF
import com.zogik.core.util.Constant.TOTAL_HP
import com.zogik.core.util.Extension.capitalizeWords
import com.zogik.core.util.RandomGenerator
import com.zogik.core.util.RandomGenerator.randomProbabilityGenerator
import com.zogik.core.util.Resource
import com.zogik.core.util.ScreenRoute
import com.zogik.feature.common.progress_indicator.CircularProgressBar
import com.zogik.feature.detail.component.animation.DetailAnimation
import com.zogik.feature.detail.component.chart.HorizontalBar
import com.zogik.feature.detail.component.header.DetailHeader
import com.zogik.feature.detail.component.list_item.ItemPokemonMove
import com.zogik.feature.detail.viewmodel.DetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val REGULAR_SPACER_HEIGHT = 8
const val SECTION_SPACER_HEIGHT = 20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    name: String,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    detailViewModel.getPokemonByName(name)
    val pokemonState = detailViewModel.pokemonState.collectAsState().value
    val pokemonData = pokemonState.data
    val frontSprites =
        pokemonData?.sprites?.versions?.generationV?.blackWhite?.animated?.front_default
    val backSprites =
        pokemonData?.sprites?.versions?.generationV?.blackWhite?.animated?.back_default
    val statDesc = listOf(TOTAL_HP, ATK, DEF, SP_ATK, SP_DEF, SPEED)
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }
    val bgColor = RandomGenerator.randomColorGenerator()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var isCaught: Boolean

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(pokemonData?.name ?: "") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        when (pokemonState) {
            is Resource.Loading -> {
                CircularProgressBar(modifier = Modifier.fillMaxSize())
            }

            is Resource.Success -> {
//                detailViewModel.cancelJob()
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(Color(bgColor))
                        .verticalScroll(scrollState),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        DetailHeader(
                            modifier = Modifier.fillMaxWidth(),
                            pokemon = pokemonData ?: SpecificPokemon()
                        ) {
                            isCaught = randomProbabilityGenerator()
                            if (isCaught) {
                                detailViewModel.catchPokemon(
                                    MyPokemon(
                                        name = pokemonData?.name.orEmpty(),
                                        imgUrl = frontSprites.orEmpty(),
                                    )
                                )
                                coroutineScope.launch {
                                    val snackbarResult = snackbarHostState.showSnackbar(
                                        message = "Gotcha!",
                                        actionLabel = "See my Pokemon",
                                        duration = SnackbarDuration.Short
                                    )
                                    when (snackbarResult) {
                                        SnackbarResult.ActionPerformed -> {
                                            navController.navigate(ScreenRoute.MyPokemon.route)
                                        }

                                        else -> {}
                                    }
                                }
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Oopsie, it runs away",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(2f / 1f)
                                .padding(horizontal = 16.dp),
                        ) {
                            DetailAnimation(
                                modifier = Modifier.weight(.3f),
                                sprites = frontSprites.orEmpty(),
                            )
                            DetailAnimation(
                                modifier = Modifier.weight(.3f),
                                sprites = backSprites.orEmpty(),
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 264.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 12,
                                    topEndPercent = 12
                                )
                            )
                            .background(Color.White)
                    ) {
                        Column {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        top = 40.dp
                                    ),
                                text = "Detail Stat",
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = REGULAR_SPACER_HEIGHT.dp,
                                    )
                            ) {
                                repeat(pokemonData?.stats?.size ?: 0) {
                                    val stat = pokemonData?.stats?.get(it)
                                    HorizontalBar(
                                        statDesc = statDesc[it],
                                        statValue = stat?.baseStat?.toFloat() ?: 0f
                                    )
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = SECTION_SPACER_HEIGHT.dp,
                                    ),
                                text = "Abilites",
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = REGULAR_SPACER_HEIGHT.dp,
                                    ),
                                text = pokemonData?.abilities?.joinToString(separator = ", ") {
                                    it.ability.name.capitalizeWords()
                                }.orEmpty(),
                                style = MaterialTheme.typography.labelMedium
                            )
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = SECTION_SPACER_HEIGHT.dp,
                                    ),
                                text = "Moves",
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = REGULAR_SPACER_HEIGHT.dp,
                                        bottom = 16.dp,
                                    ),
                                verticalArrangement = Arrangement.spacedBy(
                                    4.dp
                                ),
                            ) {
                                repeat(pokemonData?.moves?.size ?: 0) {
                                    val moveName = pokemonData?.moves?.get(it)?.move?.name
                                    ItemPokemonMove(
                                        moveName = moveName.orEmpty()
                                    ) {}
                                }
                            }
                        }
                    }
                }
            }

            else -> {}
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    PokemonTheme {
        DetailScreen(rememberNavController(), "bulbasaur")
    }
}
