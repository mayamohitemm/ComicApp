package com.example.home.homescreen

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.domain.Result
import com.example.domain.model.ComicModel
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineState = rememberCoroutineScope()
    val comicListResult = viewModel.comicDetailsState.collectAsState()
    val pagerState = rememberPagerState()

    var selectedComic by remember {
        mutableStateOf(ComicModel("", ""))
    }

    val pagerSwipeCallback = { comicModel: ComicModel ->
        selectedComic = comicModel
    }

    when (comicListResult.value) {
        is Result.Loading -> SimpleCircularProgressIndicator()
        is Result.Success -> Scaffold(
            scaffoldState,
            coroutineState,
            pagerState,
            false,
            comicListResult.value,
            pagerSwipeCallback,
            selectedComic
        )
        is Result.Error -> Scaffold(
            scaffoldState,
            coroutineState,
            pagerState,
            true,
            comicListResult.value,
            pagerSwipeCallback,
            selectedComic
        )
        else -> {

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Scaffold(
    scaffoldState: ScaffoldState,
    coroutineState: CoroutineScope,
    pagerState: PagerState,
    showError: Boolean,
    comicListResult: Result<List<ComicModel>>,
    pagerSwipeCallback: (comic: ComicModel) -> Unit,
    selectedComic: ComicModel,
) {
    var animState by remember { mutableStateOf(CardAnimationState.COLLAPSED) }

    Box {
        if (!showError) {
            Image(
                painter = rememberImagePainter(
                    data = selectedComic.imageUrl
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                alpha = 0.5f
            )
        }

        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            topBar = {
                AnimatedVisibility(
                    visible = animState == CardAnimationState.COLLAPSED,
                    content = {
                        HomeAppBar(
                            backgroundColor = Color.Transparent,
                            scaffoldState = scaffoldState,
                            coroutineState = coroutineState,
                            titleModifier = Modifier.fillMaxWidth()
                        )
                    },
                    enter = expandVertically(
                        animationSpec = tween(500, easing = LinearEasing)
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(500, easing = LinearEasing)
                    ),
                )
            },
            drawerContent = {

            },
            modifier = Modifier.systemBarsPadding(),
        ) {
            if (showError) {
                val errorMessage: String = (comicListResult as Result.Error).errorMessage
                ErrorMessage(errorMessage)
            } else {
                val comicList: List<ComicModel> =
                    (comicListResult as Result.Success<List<ComicModel>>).data
                HomeContent(
                    Modifier.fillMaxSize(),
                    comicList,
                    pagerState,
                    pagerSwipeCallback,
                    selectedComic,
                    pagerItemClick = {
                        animState = when (animState) {
                            CardAnimationState.COLLAPSED -> CardAnimationState.EXPANDED
                            CardAnimationState.EXPANDED -> CardAnimationState.COLLAPSED
                        }
                    },
                    animState = animState
                )
            }
        }
    }
}


@ExperimentalPagerApi
@Composable
fun HomeContent(
    modifier: Modifier, comicList: List<ComicModel>,
    pagerState: PagerState,
    pagerSwipeCallback: (comic: ComicModel) -> Unit,
    selectedComic: ComicModel,
    pagerItemClick: () -> Unit,
    animState: CardAnimationState,
) {
    Box(modifier = modifier) {
        Box(modifier = Modifier.align(Alignment.TopCenter)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            background = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(selectedComic.comicName.toString())
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(all = 10.dp),
            )
        }

        PagerView(comicList, pagerState, pagerSwipeCallback, pagerItemClick, animState)
    }
}

@Composable
fun HomeAppBar(
    backgroundColor: Color,
    scaffoldState: ScaffoldState,
    coroutineState: CoroutineScope,
    titleModifier: Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            background = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("MARVEL")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            background = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("STUDIOS")
                    }
                },
                textAlign = TextAlign.Center,
                modifier = titleModifier.padding(vertical = 5.dp, horizontal = 10.dp),
            )
        },
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                coroutineState.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Search, "", tint = Color.White)
            }
        },
    )
}

@ExperimentalPagerApi
@Composable
fun PagerView(
    comicList: List<ComicModel>,
    pagerState: PagerState,
    selectedComic: (comic: ComicModel) -> Unit,
    pagerItemClick: () -> Unit,
    animState: CardAnimationState,
) {
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedComic(comicList[page])
        }
    }
    Box(
        Modifier.fillMaxSize()
    ) {
        val sizeAnimation by animateFloatAsState(
            targetValue = if (animState == CardAnimationState.COLLAPSED) 0.5f else 1.0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )

        Box(
            Modifier
                .align(Alignment.BottomStart)
        ) {
            HorizontalPager(
                count = comicList.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) { page ->
                PagerItem(
                    comicModel = comicList[page],
                    onClick = {
                        pagerItemClick()
                    },
                    sizeAnimValue = sizeAnimation,
                    animState = animState
                )
            }
        }
    }
}

@Composable
fun SimpleCircularProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(errorMessage: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = errorMessage, textAlign = TextAlign.Center, color = Color.Black)
    }
}
