package com.example.comicapp.data.remote

import com.example.comicapp.data.dto.ComicDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {

    @GET("comics")
    suspend fun getComicList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20,
    ): ComicDto?
}
