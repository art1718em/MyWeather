package com.example.search.domain.usecase

import com.example.database.api.CitiesDBManager
import javax.inject.Inject

class IsCityExistUseCase @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
) {
    suspend operator fun invoke(cityName: String): Boolean{
        return citiesDBManager.isCityExist(cityName  = cityName)
    }
}