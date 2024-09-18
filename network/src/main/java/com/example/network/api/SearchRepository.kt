package com.example.network.api

import com.example.model.CityWeather
import com.example.model.Result
import com.example.model.RootError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SearchRepository {
    suspend fun getCityWeather(cityName: String): Result<CityWeather, RootError>
}