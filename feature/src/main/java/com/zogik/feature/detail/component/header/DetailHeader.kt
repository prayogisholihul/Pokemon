package com.zogik.feature.detail.component.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.theme.PokemonTheme
import com.zogik.core.util.Extension.capitalizeWords
import com.zogik.core.util.gifLoader

@Composable
fun DetailHeader(modifier: Modifier, pokemon: SpecificPokemon, onClick: () -> Unit) {

    val ctx = LocalContext.current
    val imageLoader = gifLoader(ctx)

    ConstraintLayout(
        modifier = modifier.padding(16.dp)
    ) {
        val (name, weight, height, btnCatch) = createRefs()

        Text(
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = pokemon.name.capitalizeWords(), style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier.constrainAs(weight) {
                top.linkTo(name.bottom)
                start.linkTo(parent.start)
            },
            text = "Weight ${pokemon.weight}",
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            modifier = Modifier.constrainAs(height) {
                top.linkTo(weight.bottom)
                start.linkTo(parent.start)
            },
            text = "Height ${pokemon.height}",
            style = MaterialTheme.typography.labelMedium
        )
        Button(
            modifier = Modifier
                .height(40.dp)
                .constrainAs(btnCatch) {
                    top.linkTo(weight.top)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { onClick() }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(ctx).data(com.zogik.core.R.drawable.ic_pokeball)
                            .build(),
                        imageLoader = imageLoader
                    ),
                    contentDescription = "pokemon animation",
                )
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "Catch",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailHeaderPreview() {
    PokemonTheme {
        DetailHeader(Modifier, SpecificPokemon(name = "bulbasaur", weight = 50, height = 50)) {}
    }
}
