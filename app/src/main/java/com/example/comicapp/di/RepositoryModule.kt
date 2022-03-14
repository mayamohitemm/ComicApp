package com.example.comicapp.di

import com.example.comicapp.data.ComicRepository
import com.example.comicapp.data.ComicRepositoryImpl
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
