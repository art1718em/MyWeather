package com.example.database

import com.example.database.api.CitiesDBManager
import com.example.database.data.db.FavouriteCitesDB
import com.example.database.data.dbo.CityEntity
import com.example.database.data.dbo.toCity
import com.example.model.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class CitiesDBManagerImpl(
    private val roomDatabase: FavouriteCitesDB,
) : CitiesDBManager {
    override suspend fun getAll(): Flow<List<City>> {
        return withContext(Dispatchers.IO) {
            roomDatabase.citiesDao().getAll().distinctUntilChanged()
                .map { cities -> cities.map { it.toCity() } }
        }
    }

    override suspend fun addCity(city: City) {
        withContext(Dispatchers.IO) {
            roomDatabase.citiesDao().insertCity(
                cityEntity = CityEntity(
                    id = null,
                    name = city.name,
                    isSelected = city.isSelected,
                )
            )
        }
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
}