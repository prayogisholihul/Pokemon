package com.zogik.feature.my_pokemon.component.list_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.zogik.core.domain.model.MyPokemon
import com.zogik.core.theme.PokemonTheme
import com.zogik.core.util.Extension.capitalizeWords
import com.zogik.core.util.RandomGenerator.randomColorGenerator
import com.zogik.core.util.gifLoader

@Composable
fun ItemMyPokemon(data: MyPokemon, onClick: () -> Unit, onDelete: () -> Unit) {

    val ctx = LocalContext.current
    val imageLoader = gifLoader(ctx)
    val color = randomColorGenerator()

    Card(
        modifier = Modifier
            .aspectRatio(1f / 1f)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(color)),
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (img, name, btnDelete, owned) = createRefs()
            createVerticalChain(img, name, chainStyle = ChainStyle.Packed)

            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .background(shape = RoundedCornerShape(percent = 100), color = Color.Red)
                    .constrainAs(btnDelete) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                onClick = { onDelete() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
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
                    ImageRequest.Builder(ctx).data(data.imgUrl).apply(block = {
                        size(Size.ORIGINAL)
                    }).build(),
                    imageLoader = imageLoader
                ),
                contentDescription = "Pokemon Animation",
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(img.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = data.name.capitalizeWords(),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Text(
                modifier = Modifier.constrainAs(owned) {
                    top.linkTo(name.bottom, 4.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "Owned ${data.owned}",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemPokemonPreview() {
    PokemonTheme {
        ItemMyPokemon(MyPokemon(name = "bulbasaur"), onClick = {}, onDelete = {})
    }
}
