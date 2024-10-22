package com.example.favourites.domain.usecase

import android.util.Log
import com.example.database.api.CitiesDBManager
import javax.inject.Inject

class ChangeSelectedCityUseCase @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
) {
    suspend operator fun invoke(cityName: String){
        citiesDBManager.updateSelectedCity(cityName = cityName)
    }
}