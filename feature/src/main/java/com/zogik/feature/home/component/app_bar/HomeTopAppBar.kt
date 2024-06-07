package com.zogik.feature.home.component.app_bar

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.zogik.core.util.gifLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(onClick: () -> Unit) {
    val ctx = LocalContext.current
    val imageLoader = gifLoader(ctx)

    TopAppBar(
        title = { Text("Home") },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = {
            IconButton(onClick = { onClick() }) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(ctx).data(com.zogik.core.R.drawable.ic_pokeball)
                            .apply(block = {
                                size(100)
                            }).build(),
                        imageLoader = imageLoader
                    ),
                    contentDescription = "pokeball action image",
                )
            }
        }
    )
}
