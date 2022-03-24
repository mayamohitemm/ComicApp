package com.example.data.injection

import com.example.data.remote.RequestInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

@Module
@InstallIn(SingletonComponent::class)
abstract class RetrofitModule {

    @Binds
    abstract fun bindInterceptor(interceptor: RequestInterceptor): Interceptor

    companion object {
        @Provides
        fun provideRetrofit(client: OkHttpClient): com.example.data.remote.ComicApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(com.example.data.remote.ComicApi::class.java)
        }

        @Provides
        fun provideClient(interceptor: Interceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }
    }
}
