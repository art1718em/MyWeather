package com.example.search.ui.state

sealed interface CityWeatherEvent {
    data object OnEnterScreen : CityWeatherEvent
    data object OnSearchCity : CityWeatherEvent
    data class OnEditSearch(val cityName: String) : CityWeatherEvent
}