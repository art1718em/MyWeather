package com.example.database.api

import com.example.model.CityWeather
import kotlinx.coroutines.flow.Flow

interface CitiesDBManager {

    suspend fun getAll(): Flow<List<CityWeather>>

    suspend fun addCity(cityName: String)

    suspend fun deleteCity(cityName: String)

    suspend fun changeIsSelected(cityName: String, isSelected: Boolean)

    suspend fun getSelectedCity(): Flow<String?>

    suspend fun unselectCity()

    suspend fun isCityExist(cityName: String): Boolean

    suspend fun getFirstCity(): String?
}