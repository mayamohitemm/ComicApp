package com.example.comicapp.data

import com.example.comicapp.home.ComicModel
import com.example.comicapp.util.CoroutineDispatcherProvider
import com.example.comicapp.util.UiState
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val remoteComicDataSource: ComicDataSource,
    private val dispatcher: CoroutineDispatcherProvider,
) : ComicRepository {

    override suspend fun getComicList(): UiState<List<ComicModel>> {
        return withContext(dispatcher.io) {
            remoteComicDataSource.getComicList()
        }
    }
}
