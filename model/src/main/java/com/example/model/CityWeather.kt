package com.example.model

data class CityWeather(
    val name: String,
    val description: String,
    val temperature: Double,
    val temperatureFeelsLike: Double,
    val humidity: Int,
    val pressure: Int,
)