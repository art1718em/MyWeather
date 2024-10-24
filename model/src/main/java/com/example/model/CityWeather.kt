package com.example.model

data class CityWeather(
    val name: String,
    val description: WeatherDescription?,
    val temperature: Int?,
    val temperatureFeelsLike: Int?,
    val humidity: Int?,
    val pressure: Int?,
)