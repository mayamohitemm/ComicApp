package com.example.home.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun PagerItem(
    imageUrl: String?,
    width: Float,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(width)
            .fillMaxHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = 16.dp
    ) {

        Image(
            painter = rememberImagePainter(
                data = remember { imageUrl }
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onClick()
                }
        )
    }
}
