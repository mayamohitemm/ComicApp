package com.example.home.homescreen

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.domain.model.ComicModel

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun PagerItem(
    comicModel: ComicModel,
    onClick: () -> Unit,
    sizeAnimValue: Float,
    animState: CardAnimationState,
) {
    val shapeAnimation by animateDpAsState(
        if (animState == CardAnimationState.COLLAPSED) 16.dp else 0.dp,
    )
    Card(
        modifier = Modifier
            .heightIn(300.dp)
            .fillMaxWidth(sizeAnimValue)
            .fillMaxHeight(sizeAnimValue),
        shape = RoundedCornerShape(shapeAnimation),
    ) {
        Box {
            Image(
                painter = rememberImagePainter(
                    data = remember { comicModel.imageUrl }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.BottomCenter)
                    .clickable {
                        onClick()
                    }
            )
            AnimatedVisibility(
                visible = animState == CardAnimationState.EXPANDED,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(20.dp),
                content = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    background = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(comicModel.comicName.toString())
                            }
                        }
                    )
                },
                enter = fadeIn(
                    animationSpec = tween(500, easing = LinearEasing)
                ),
                exit = fadeOut(
                    animationSpec = tween(500, easing = LinearEasing)
                ),
            )
        }
    }
}
