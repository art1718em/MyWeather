package com.example.search.domain.usecase

import com.example.database.api.CitiesDBManager
import com.example.model.City

class LikeCityUseCase(
    private val citiesDBManager: CitiesDBManager,
) {
    internal suspend operator fun invoke(city: City) {
        citiesDBManager.unselectCity()
        citiesDBManager.addCity(city = city)
    }
}