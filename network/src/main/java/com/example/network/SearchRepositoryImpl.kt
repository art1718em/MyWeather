package com.example.network

import android.util.Log
import com.example.model.CityWeather
import com.example.model.CoreError
import com.example.model.RootError
import com.example.network.api.SearchRepository
import com.example.network.data.SearchApi
import com.example.network.dto.toCityWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.model.Result
import com.example.network.utils.convertCodeToProfileError

internal class SearchRepositoryImpl(
    private val searchApi: SearchApi,
) : SearchRepository {

    override suspend fun getCityWeather(cityName: String): Result<CityWeather, RootError> {
           return withContext(Dispatchers.IO) {
            try{
                val response = searchApi.getCityWeather(cityName = cityName)
                if (response.isSuccessful){
                    response.body()?.let { cityWeatherDto ->
                        return@withContext Result.Success(data = cityWeatherDto.toCityWeather())
                    }
                }
                return@withContext Result.Error(data = null, error = response.convertCodeToProfileError())
            } catch (exception: Exception){
                return@withContext Result.Error(data = null, error = CoreError.CONNECTION_ERROR)
            }
        }
    }
}

