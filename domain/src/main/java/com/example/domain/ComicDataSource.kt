package com.example.domain

import com.example.domain.model.ComicModel

interface ComicDataSource {
    suspend fun getComicList(): Result<List<ComicModel>>
}
