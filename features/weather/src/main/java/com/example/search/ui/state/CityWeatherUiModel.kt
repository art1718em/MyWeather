package com.example.search.ui.state

import com.example.model.WeatherDescription

data class CityWeatherUiModel(
    val name: String,
    val description: WeatherDescription?,
    val temperature: Int?,
    val temperatureFeelsLike: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val isLiked: Boolean,
){
    companion object{
        val default = CityWeatherUiModel(
            name = "",
            description =  WeatherDescription.CLEARLY,
            temperature = 0,
            temperatureFeelsLike = 0,
            humidity = 0,
            pressure = 0,
            isLiked = false,
        )
    }
}