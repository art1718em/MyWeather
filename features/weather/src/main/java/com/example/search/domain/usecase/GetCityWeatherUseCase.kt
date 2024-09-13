package com.example.search.domain.usecase

import com.example.model.CityWeather
import com.example.network.api.SearchRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(cityName: String): Result<CityWeather>{
        return searchRepository.getCityWeather(cityName = cityName)
    }
}