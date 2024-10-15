package com.example.favourites.domain.usecase

import com.example.database.api.CitiesDBManager
import javax.inject.Inject

class GetFirstCityUseCase @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
) {
    suspend operator fun invoke(): String?{
        return citiesDBManager.getFirstCity()
    }
}