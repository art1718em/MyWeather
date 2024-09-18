package com.example.search.ui.state

import com.example.model.RootError

data class CityWeatherScreenState(
    val cityWeatherUiModel: CityWeatherUiModel,
    val isLoading: Boolean,
    val error: RootError?,
){
    companion object{
        val default = CityWeatherScreenState(
            cityWeatherUiModel = CityWeatherUiModel.default,
            isLoading = false,
            error = null,
        )
    }
}