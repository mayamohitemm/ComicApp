package com.example.comicapp.data.mapper

import com.example.comicapp.data.Mapper
import com.example.comicapp.data.dto.ComicDto
import com.example.comicapp.home.ComicModel
import javax.inject.Inject

class ComicModelMapper @Inject constructor(
) : Mapper<ComicDto?, List<ComicModel>>() {

    override fun mapTo(from: ComicDto?): List<ComicModel> {
        val comicList = mutableListOf<ComicModel>()
        from?.let {
            it.comicData?.comicDetails?.forEach { comicDetail ->
                comicList.add(
                    ComicModel(
                        comicName = comicDetail.title,
                        imageUrl = "${comicDetail.thumbnail?.path}.${comicDetail.thumbnail?.extension}"
                    )
                )
            }
        }
        return comicList
    }
}
