package com.zogik.feature.common.list_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.zogik.core.util.gifLoader

@Composable
fun ItemEmpty(modifier: Modifier) {
    val ctx = LocalContext.current
    val imageLoader = gifLoader(ctx)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(ctx).data(com.zogik.core.R.drawable.ic_pokeball).build(),
                imageLoader = imageLoader
            ),
            contentDescription = "pokeball-animation",
        )
        Text(text = "No Data")
    }
}
