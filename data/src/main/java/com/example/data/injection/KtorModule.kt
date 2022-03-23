package com.example.data.injection

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.http.*
import io.ktor.util.*
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Singleton

private const val TIME_OUT = 60_000
private const val API_KEY = "b61f5c2fc1698647aa25114c18be70be"
private const val PRIVATE_API_KEY = "9c32aec9dc7f8317a34ab93682c97fceadee25d4"

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @OptIn(InternalAPI::class)
    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {

            val timestamp = System.currentTimeMillis().toString()
            val hash = getHash(timestamp)

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor : ", message)
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status : ", "${response.status.value}")
                }
            }

            defaultRequest {
                url {
                    parameters.appendAll(
                        parametersOf(
                            "apikey" to listOf(API_KEY),
                            "ts" to listOf(timestamp),
                            "hash" to listOf(hash),
                        )
                    )
                }
            }
        }
    }
}

private fun getHash(timestamp: String): String {
    val md = MessageDigest.getInstance("MD5")
    val hash = BigInteger(
        1,
        md.digest("$timestamp${PRIVATE_API_KEY}${API_KEY}".toByteArray())
    ).toString(16).padStart(32, '0')
    println("Generated hash : $hash")
    return hash
}