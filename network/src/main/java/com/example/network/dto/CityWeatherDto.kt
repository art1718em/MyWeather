package com.example.network.dto

import com.example.model.CityWeather
import com.google.gson.annotations.SerializedName

internal data class CityWeatherDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("weather")
    val weatherDto: List<WeatherDto>,
    @SerializedName("main")
    val mainDto: MainDto,
)

internal fun CityWeatherDto.toCityWeather(): CityWeather{
    return CityWeather(
        name = name,
        description =  weatherDto[0].description,
        temperature = mainDto.temperature,
        temperatureFeelsLike = mainDto.temperatureFeelsLike,
        humidity = mainDto.humidity,
        pressure = mainDto.pressure,
    )
}

