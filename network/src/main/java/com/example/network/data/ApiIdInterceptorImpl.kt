package com.example.network.data

import android.util.Log
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiIdInterceptorImpl : ApiIdInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "0934ae1c8876554b925bf4f264bb474d"
        val request = chain
            .request()
            .newBuilder()
            .addHeader("ApiId", token)
            .build()

        return chain.proceed(request)
    }
}