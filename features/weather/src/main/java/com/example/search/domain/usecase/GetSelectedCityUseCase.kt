package com.example.search.domain.usecase

import com.example.database.api.CitiesDBManager
import kotlinx.coroutines.flow.Flow

class GetSelectedCityUseCase(
    private val citiesDBManager: CitiesDBManager,
) {
    internal suspend operator fun invoke(): String? {
        return citiesDBManager.getSelectedCity()
    }
}