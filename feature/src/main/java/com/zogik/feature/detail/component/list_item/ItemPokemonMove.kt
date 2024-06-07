package com.zogik.feature.detail.component.list_item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zogik.core.theme.PokemonTheme
import com.zogik.core.util.Extension.capitalizeWords

@Composable
fun ItemPokemonMove(moveName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(size = 8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = moveName.replace('-', ' ').capitalizeWords(),
                style = MaterialTheme.typography.labelMedium
            )
//            Icon(
//                modifier = Modifier.size(16.dp),
//                painter = painterResource(com.zogik.core.R.drawable.ic_baseline_open_in_new),
//                contentDescription = "open"
//            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemPokemonMovePreview() {
    PokemonTheme {
        ItemPokemonMove("Cut") {}
    }
}
