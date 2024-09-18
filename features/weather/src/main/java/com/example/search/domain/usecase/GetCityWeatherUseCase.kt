package com.example.search.domain.usecase

import com.example.model.CityWeather
import com.example.model.Result
import com.example.model.RootError
import com.example.network.api.SearchRepository
import javax.inject.Inject

internal class GetCityWeatherUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    internal suspend operator fun invoke(cityName: String): Result<CityWeather, RootError>{
        return searchRepository.getCityWeather(cityName = cityName)
    }
}