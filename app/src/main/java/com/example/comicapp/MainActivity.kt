package com.example.comicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home.homescreen.HomeScreen
import com.example.home.homescreen.HomeScreenViewModel
import com.example.commonui.theme.ComicAppTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }

            ComicAppTheme {
                ProvideWindowInsets {
                    Surface(
                        color = MaterialTheme.colors.background
                    ) {
                        val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                        HomeScreen(
                            homeScreenViewModel
                        )
                    }
                }
            }
        }
    }
}
