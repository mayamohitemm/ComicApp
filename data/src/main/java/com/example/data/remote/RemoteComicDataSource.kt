package com.example.data.remote

import com.example.data.mapper.ComicModelMapper
import com.example.domain.Result
import com.example.domain.model.ComicModel
import java.lang.Exception
import javax.inject.Inject

class RemoteComicDataSource @Inject constructor(
    private val comicApi: ComicApi,
    private val mapper: ComicModelMapper,
) : com.example.domain.ComicDataSource {

    override suspend fun getComicList(): Result<List<ComicModel>> {
        return try {
            val result = comicApi.getComicList()
            Result.Success(mapper.mapTo(result))
        } catch (ex: Exception) {
            Result.Error(ex.message.toString())
        }
    }
}
