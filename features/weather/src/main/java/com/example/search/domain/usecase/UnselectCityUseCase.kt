package com.example.search.domain.usecase

import com.example.database.api.CitiesDBManager
import javax.inject.Inject

class UnselectCityUseCase @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
) {
    suspend operator fun invoke(){
        citiesDBManager.unselectCity()
    }
}