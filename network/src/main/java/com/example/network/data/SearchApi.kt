package com.example.network.data

import com.example.network.dto.CityWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchApi {

    @GET(".")
    suspend fun getCityWeather(
        @Query("q") cityName: String,
        @Query("lang") lang: String = "ru"
    ): Response<CityWeatherDto>
}