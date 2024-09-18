package com.example.search.ui.state

data class CityWeatherUiModel(
    val name: String,
    val description: String,
    val temperature: Double,
    val temperatureFeelsLike: Double,
    val humidity: Int,
    val pressure: Int,
){
    companion object{
        val default = CityWeatherUiModel(
            name = "",
            description =  "",
            temperature = 0.0,
            temperatureFeelsLike = 0.0,
            humidity = 0,
            pressure = 0,
        )
    }
}