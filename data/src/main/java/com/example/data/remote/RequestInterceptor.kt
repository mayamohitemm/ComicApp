package com.example.data.remote

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

private const val API_KEY = "b61f5c2fc1698647aa25114c18be70be"
private const val PRIVATE_API_KEY = "9c32aec9dc7f8317a34ab93682c97fceadee25d4"

class RequestInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val timestamp = System.currentTimeMillis().toString()
        val hash = getHash(timestamp)

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun getHash(timestamp: String): String {
        val md = MessageDigest.getInstance("MD5")
        val hash = BigInteger(
            1,
            md.digest("$timestamp$PRIVATE_API_KEY$API_KEY".toByteArray())
        ).toString(16).padStart(32, '0')
        println("Generated hash : $hash")
        return hash
    }
}
