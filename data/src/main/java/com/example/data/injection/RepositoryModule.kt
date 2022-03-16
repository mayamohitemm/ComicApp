package com.example.data.injection

import com.example.data.repository.ComicRepositoryImpl
import com.example.domain.ComicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(comicRepositoryImpl: ComicRepositoryImpl): ComicRepository
}
