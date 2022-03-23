package com.example.data.remote

import com.example.data.dto.ComicDto
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

class ComicApi @Inject constructor(private val client: HttpClient) {

    suspend fun getComicList(
    ): ComicDto? = client.get("${BASE_URL}comics")

}
