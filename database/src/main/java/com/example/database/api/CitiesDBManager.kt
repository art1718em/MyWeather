package com.example.database.api

import com.example.model.City
import kotlinx.coroutines.flow.Flow

interface CitiesDBManager {

    suspend fun getAll(): Flow<List<City>>

    suspend fun addCity(city: City)

    suspend fun deleteCity(cityName: String)

    suspend fun changeIsSelected(cityName: String, isSelected: Boolean)

    suspend fun getSelectedCity(): String?

    suspend fun unselectCity()
}