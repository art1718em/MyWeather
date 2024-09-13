package com.example.network

import com.example.model.CityWeather
import com.example.network.api.SearchRepository
import com.example.network.data.SearchApi
import com.example.network.dto.toCityWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SearchRepositoryImpl(
    private val searchApi: SearchApi,
) : SearchRepository {

    override suspend fun getCityWeather(cityName: String): Result<CityWeather> {
        return withContext(Dispatchers.IO) {
            try{
                val response = searchApi.getCityWeather(cityName = cityName)
                if (response.isSuccessful){
                    response.body()?.let { cityWeatherDto ->
                        Result.success(
                            value = cityWeatherDto.toCityWeather()
                        )
                    }
                }
                Result.failure(Exception())
            } catch (exception: Exception){
                Result.failure(exception)
            }

        }
    }
}