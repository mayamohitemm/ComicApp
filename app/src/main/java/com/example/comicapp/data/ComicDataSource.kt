package com.example.comicapp.data

import com.example.comicapp.home.ComicModel
import com.example.comicapp.util.UiState

interface ComicDataSource {
    suspend fun getComicList(): UiState<List<ComicModel>>
}