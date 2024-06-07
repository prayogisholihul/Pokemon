package com.zogik.feature.detail.component.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zogik.core.theme.Orange
import com.zogik.core.theme.PokemonTheme

@Composable
fun HorizontalBar(statDesc: String, statValue: Float) {

    val greyMaxStatWidth = .8f
    val statPercentage = statValue * greyMaxStatWidth / 100f

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        val (desc, bg, valueBar) = createRefs()

        Text(
            modifier = Modifier
                .fillMaxWidth(.2f)
                .constrainAs(desc) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            text = statDesc, style = MaterialTheme.typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(greyMaxStatWidth)
                .height(12.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Gray)
                .constrainAs(bg) {
                    end.linkTo(parent.end)
                    top.linkTo(desc.top)
                    bottom.linkTo(desc.bottom)
                }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(statPercentage)
                .height(14.dp)
                .clip(RoundedCornerShape(50))
                .background(color = Orange)
                .padding(horizontal = 4.dp)
                .constrainAs(valueBar) {
                    start.linkTo(bg.start)
                    top.linkTo(bg.top)
                    bottom.linkTo(bg.bottom)
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = statValue.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailStatsPreview() {
    PokemonTheme {
        Column {
            HorizontalBar("HP", 50f)
            Spacer(modifier = Modifier.height(2.dp))
            HorizontalBar("Atk", 60f)
            Spacer(modifier = Modifier.height(2.dp))
            HorizontalBar("Speed", 29f)
        }
    }
}
