package com.example.search.ui.state

import android.content.Context

sealed interface CityWeatherEvent {
    data object OnEnterScreen : CityWeatherEvent
    data class OnSearchCity(val cityName: String) : CityWeatherEvent
    data class OnLikeCity(val cityName: String) : CityWeatherEvent
    data class OnSendNotification(val context: Context) : CityWeatherEvent
}
