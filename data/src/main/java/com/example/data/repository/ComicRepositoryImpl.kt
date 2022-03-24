package com.example.data.repository

import com.example.common.data.dispatcher.CoroutineDispatcherProvider
import com.example.domain.model.ComicModel
import com.example.domain.ComicRepository
import com.example.domain.Result
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val remoteComicDataSource: com.example.domain.ComicDataSource,
    private val dispatcher: CoroutineDispatcherProvider,
) : ComicRepository {

    override suspend fun getComicList(): Result<List<ComicModel>> {
        return withContext(dispatcher.io) {
            remoteComicDataSource.getComicList()
        }
    }
}
