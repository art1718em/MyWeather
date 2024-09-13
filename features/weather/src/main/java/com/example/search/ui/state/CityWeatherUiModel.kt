package com.example.search.ui.state

data class CityWeatherUiModel(
    val name: String,
    val description: String,
    val temperature: Double,
    val temperatureFeelsLike: Double,
    val humidity: Int,
    val pressure: Int,
)