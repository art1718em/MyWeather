package com.example.search.domain.usecase

import com.example.database.api.CitiesDBManager
import javax.inject.Inject

internal class LikeCityUseCase @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
) {
    internal suspend operator fun invoke(cityName: String) {
        citiesDBManager.unselectCity()
        citiesDBManager.addCity(cityName = cityName)
    }
}