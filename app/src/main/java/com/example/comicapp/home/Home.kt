package com.example.comicapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.comicapp.util.UiState
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
        is UiState.Loading -> SimpleCircularProgressIndicator()
        is UiState.Success -> Scaffold(
            scaffoldState,
            coroutineState,
            pagerState,
            false,
            comicListResult.value,
            pagerSwipeCallback,
            selectedComic
        )
        is UiState.Error -> Scaffold(
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
    comicListResult: UiState<List<ComicModel>>,
    pagerSwipeCallback: (comic: ComicModel) -> Unit,
    selectedComic: ComicModel,
) {
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
                HomeAppBar(
                    backgroundColor = Color.Transparent,
                    scaffoldState = scaffoldState,
                    coroutineState = coroutineState,
                    titleModifier = Modifier.fillMaxWidth()
                )
            },
            drawerContent = {

            },
            modifier = Modifier.systemBarsPadding(),
        ) {
            if (showError) {
                val errorMessage: String = (comicListResult as UiState.Error).errorMessage
                ErrorMessage(errorMessage)
            } else {
                val comicList: List<ComicModel> =
                    (comicListResult as UiState.Success<List<ComicModel>>).data
                HomeContent(
                    Modifier.fillMaxSize(),
                    comicList,
                    pagerState,
                    pagerSwipeCallback,
                    selectedComic
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

        PagerView(comicList, pagerState, pagerSwipeCallback)
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
    selectedComic: (comic: ComicModel) -> Unit
) {
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedComic(comicList[page])
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Box(
            Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 16.dp)
        ) {
            HorizontalPager(
                count = comicList.size,
                state = pagerState,
                contentPadding = PaddingValues(all = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) { page ->
                PagerItem(
                    imageUrl = comicList[page].imageUrl
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


