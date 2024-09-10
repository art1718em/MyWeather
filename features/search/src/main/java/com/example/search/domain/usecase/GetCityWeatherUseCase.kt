package com.example.search.domain.usecase

import com.example.network.api.SearchRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
}