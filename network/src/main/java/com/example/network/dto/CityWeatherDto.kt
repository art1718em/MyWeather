package com.example.network.dto

import com.example.model.CityWeather
import com.example.model.WeatherDescription
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
        description =  fromString(weatherDto[0].description),
        // перевод из кельвинов в градусы цельсия
        temperature = (mainDto.temperature - 273).roundToInt(),
        // перевод из кельвинов в градусы цельсия
        temperatureFeelsLike = (mainDto.temperatureFeelsLike - 273).roundToInt(),
        humidity = mainDto.humidity,
        // перевод из паскалей в мм ртутного столба
        pressure = (mainDto.pressure * 0.75).toInt(),
    )
}

private fun fromString(string: String): WeatherDescription {
    return when (string) {
        "ясно" -> WeatherDescription.CLEARLY
        "пасмурно" -> WeatherDescription.DULL
        "небольшая облачность" -> WeatherDescription.SLIGHT_CLOUD_COVER
        "облачно с прояснениями" -> WeatherDescription.CLOUDY_WITH_CLARIFICATIONS
        "переменная облачность" -> WeatherDescription.PARTLY_CLOUDY
        "дождь" -> WeatherDescription.RAIN
        "небольшой дождь" -> WeatherDescription.LIGHT_RAIN
        "снег" -> WeatherDescription.SNOW
        "ливень" -> WeatherDescription.RAINFALL
        "гроза" -> WeatherDescription.THUNDERSTORM
        "туман" -> WeatherDescription.FOG
        else -> WeatherDescription.MIST
    }
}


