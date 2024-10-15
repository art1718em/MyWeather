package com.example.search.ui.state

data class CityWeatherUiModel(
    val name: String,
    val description: String?,
    val temperature: Int?,
    val temperatureFeelsLike: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val isLiked: Boolean,
){
    companion object{
        val default = CityWeatherUiModel(
            name = "",
            description =  "",
            temperature = 0,
            temperatureFeelsLike = 0,
            humidity = 0,
            pressure = 0,
            isLiked = false,
        )
    }
}