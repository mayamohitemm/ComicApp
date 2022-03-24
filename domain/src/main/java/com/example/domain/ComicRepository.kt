package com.example.domain

import com.example.domain.model.ComicModel

interface ComicRepository {

    suspend fun getComicList(): Result<List<ComicModel>>
}
