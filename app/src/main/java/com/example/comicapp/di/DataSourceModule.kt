package com.example.comicapp.di

import com.example.comicapp.data.ComicDataSource
import com.example.comicapp.data.remote.RemoteComicDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteComicDataSource: RemoteComicDataSource): ComicDataSource
}
