package com.example.search.ui.state

data class CityWeatherScreenState(
    val cityWeatherUiModel: CityWeatherUiModel,
    val isLoading: Boolean,
    val error: Error?,
)