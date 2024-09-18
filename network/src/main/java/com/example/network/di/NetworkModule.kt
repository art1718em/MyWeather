package com.example.network.di

import com.example.network.SearchRepositoryImpl
import com.example.network.api.SearchRepository
import com.example.network.data.ApiIdInterceptor
import com.example.network.data.ApiIdInterceptorImpl
import com.example.network.data.SearchApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideAppIdInterceptor(): ApiIdInterceptor{
        return ApiIdInterceptorImpl()
    }

    @Singleton
    @Provides
    internal fun provideClient(apiIdInterceptor: ApiIdInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(apiIdInterceptor)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideSearchApi(retrofit: Retrofit): SearchApi{
        return retrofit.create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideSearchRepository(searchApi: SearchApi): SearchRepository{
        return SearchRepositoryImpl(searchApi)
    }
}