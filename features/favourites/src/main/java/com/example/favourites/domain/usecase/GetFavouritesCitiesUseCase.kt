package com.example.favourites.domain.usecase

import com.example.database.api.CitiesDBManager
import com.example.model.CityWeather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetFavouritesCitiesUseCase @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
) {
    suspend operator fun invoke(): Flow<List<CityWeather>> {
        return citiesDBManager.getAll()
    }
}