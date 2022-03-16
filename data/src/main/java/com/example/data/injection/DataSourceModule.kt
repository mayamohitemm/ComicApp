package com.example.data.injection

import com.example.data.remote.RemoteComicDataSource
import com.example.domain.ComicDataSource
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
