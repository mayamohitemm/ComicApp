package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicDto(
    @SerialName("data")
    val comicData: ComicData?
)

@Serializable
data class ComicData(
    @SerialName( "offset")
    val offset: Int,
    @SerialName( "limit")
    val limit: Int,
    @SerialName( "total")
    val total: Int,
    @SerialName( "count")
    val count: Int,
    @SerialName( "results")
    val comicDetails: List<ComicDetail>,
)

@Serializable
data class ComicDetail(
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("pageCount")
    val pageCount: Int?,
    @SerialName("format")
    val format: String?,
    @SerialName("textObjects")
    val information: List<Information>?,
    @SerialName("prices")
    val prices: List<Price>?,
    @SerialName("thumbnail")
    val thumbnail: Image?,
    @SerialName("images")
    val images: List<Image>?,
)

@Serializable
data class Information(
    @SerialName("type") val type: String?,
    @SerialName("language") val language: String?,
    @SerialName("text") val text: String?,
)

@Serializable
data class Price(
    @SerialName("type") val type: String?,
    @SerialName("price") val price: Float?,
)

@Serializable
data class Image(
    @SerialName("path") val path: String?,
    @SerialName("extension") val extension: String?,
)
