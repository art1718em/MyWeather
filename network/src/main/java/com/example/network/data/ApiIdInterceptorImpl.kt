package com.example.network.data

import android.util.Log
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiIdInterceptorImpl : ApiIdInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        // Add the API key as a query parameter
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("appid", "0934ae1c8876554b925bf4f264bb474d")
            .build()

        // Build a new request with the updated URL
        val newRequest = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }
}