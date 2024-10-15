package com.example.network.dto

import com.example.model.CityWeather
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

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
        // перевод из кельвинов в градусы цельсия
        temperature = (mainDto.temperature - 273).roundToInt(),
        // перевод из кельвинов в градусы цельсия
        temperatureFeelsLike = (mainDto.temperatureFeelsLike - 273).roundToInt(),
        humidity = mainDto.humidity,
        // перевод из паскалей в мм ртутного столба
        pressure = (mainDto.pressure * 0.75).toInt(),
    )
}

