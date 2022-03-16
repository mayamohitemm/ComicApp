package com.example.data.dto

import com.squareup.moshi.Json

data class ComicDto(
    @field:Json(name = "data")
    val comicData: ComicData?
)

data class ComicData(
    @field:Json(name = "offset")
    val offset: Int,
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "count")
    val count: Int,
    @field:Json(name = "results")
    val comicDetails: List<ComicDetail>,
)

data class ComicDetail(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "pageCount")
    val pageCount: Int?,
    @field:Json(name = "format")
    val format: String?,
    @field:Json(name = "textObjects")
    val information: List<Information>?,
    @field:Json(name = "prices")
    val prices: List<Price>?,
    @field:Json(name = "thumbnail")
    val thumbnail: Image?,
    @field:Json(name = "images")
    val images: List<Image>?,
)

data class Information(
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "text") val text: String?,
)

data class Price(
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "price") val price: Float?,
)

data class Image(
    @field:Json(name = "path") val path: String?,
    @field:Json(name = "extension") val extension: String?,
)
