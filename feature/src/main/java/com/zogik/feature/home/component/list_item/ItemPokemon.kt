package com.zogik.feature.home.component.list_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.domain.model.Sprites
import com.zogik.core.theme.PokemonTheme
import com.zogik.core.util.Extension.capitalizeWords
import com.zogik.core.util.RandomGenerator.randomColorGenerator
import com.zogik.core.util.gifLoader

@Composable
fun ItemPokemon(data: SpecificPokemon, onClick: () -> Unit) {

    val ctx = LocalContext.current
    val imageLoader = gifLoader(ctx)
    val color = randomColorGenerator()

    Card(
        modifier = Modifier
            .aspectRatio(1f / 1f)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(color))
    ) {
        ConstraintLayout {
            val (img, name) = createRefs()
            createVerticalChain(img, name, chainStyle = ChainStyle.Packed)

            Image(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .aspectRatio(1f / 1f)
                    .constrainAs(img) {
                        top.linkTo(parent.top)
                        bottom.linkTo(name.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(ctx).data(
                        data.sprites.versions.generationV.blackWhite.animated.front_default
                    ).apply(block = {
                        size(Size.ORIGINAL)
                    }).build(),
                    imageLoader = imageLoader
                ),
                contentDescription = "pokemon image",
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(img.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = data.name.capitalizeWords(),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemPokemonPreview() {
    PokemonTheme {
        ItemPokemon(
            SpecificPokemon(
                name = "bulbasaur",
                sprites = Sprites(frontDefault = "https://rb.gy/cq38ca")
            )
        ) {}
    }
}

