package com.example.domain.usecase

import com.example.domain.ComicRepository
import com.example.domain.Result
import com.example.domain.model.ComicModel
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(
    private val comicRepository: ComicRepository,
) : BaseUseCase {

    suspend fun getComicList(): Result<List<ComicModel>> {
        return comicRepository.getComicList()
    }
}
