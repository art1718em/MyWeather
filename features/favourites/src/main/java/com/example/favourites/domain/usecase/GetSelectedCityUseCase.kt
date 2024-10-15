package com.example.favourites.domain.usecase

import com.example.database.api.CitiesDBManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetSelectedCityUseCase @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
) {
    internal suspend operator fun invoke(): Flow<String?> {
        return citiesDBManager.getSelectedCity()
    }
}