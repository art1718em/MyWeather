package com.example.database

import android.util.Log
import com.example.database.api.CitiesDBManager
import com.example.database.data.db.FavouriteCitesDB
import com.example.database.data.dbo.CityEntity
import com.example.database.data.dbo.toCityWeather
import com.example.model.CityWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class CitiesDBManagerImpl(
    private val roomDatabase: FavouriteCitesDB,
) : CitiesDBManager {
    override suspend fun getAll(): Flow<List<CityWeather>> {
        return withContext(Dispatchers.IO) {
            roomDatabase.citiesDao().getAll().distinctUntilChanged()
                .map { cities -> cities.map { it.toCityWeather() } }
        }
    }

    override suspend fun addCity(cityName: String) {
        withContext(Dispatchers.IO) {
            roomDatabase.citiesDao().insertCity(
                cityEntity = CityEntity(
                    id = null,
                    name = cityName,
                    isSelected = true,
                )
            )
        }

        unselectCity()
        changeIsSelected(
            cityName = cityName,
            isSelected = true,
        )
    }

    override suspend fun deleteCity(cityName: String) {
        withContext(Dispatchers.IO) {
            roomDatabase.citiesDao().deleteCity(cityName = cityName)
        }
    }

    override suspend fun changeIsSelected(cityName: String, isSelected: Boolean) {
        withContext(Dispatchers.IO) {
            roomDatabase.citiesDao().changeIsSelected(
                cityName = cityName,
                isSelected = isSelected,
            )
        }
    }

    override suspend fun getSelectedCity(): Flow<String?> {
        return withContext(Dispatchers.IO){
            roomDatabase.citiesDao().getSelectedCity()
        }
    }

    override suspend fun unselectCity() {
        withContext(Dispatchers.IO){
            roomDatabase.citiesDao().unselectCity()
        }
    }

    override suspend fun isCityExist(cityName: String): Boolean {
        return withContext(Dispatchers.IO){
            roomDatabase.citiesDao().isCityExists(cityName = cityName) > 0
        }
    }

    override suspend fun getFirstCity(): String? {
        return withContext(Dispatchers.IO){
            roomDatabase.citiesDao().getFirstItem()?.name
        }
    }
}