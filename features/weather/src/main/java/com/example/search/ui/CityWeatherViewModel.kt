package com.example.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.City
import com.example.model.CityWeather
import com.example.model.Result
import com.example.search.domain.model.WeatherError
import com.example.search.domain.usecase.GetCityWeatherUseCase
import com.example.search.domain.usecase.GetSelectedCityUseCase
import com.example.search.domain.usecase.LikeCityUseCase
import com.example.search.ui.state.CityWeatherEvent
import com.example.search.ui.state.CityWeatherScreenState
import com.example.search.ui.state.CityWeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CityWeatherViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val getSelectedCityUseCase: GetSelectedCityUseCase,
    private val likeCityUseCase: LikeCityUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CityWeatherScreenState.default)
    val state = _state.asStateFlow()

    fun obtainEvent(event: CityWeatherEvent) {
        when (event) {
            is CityWeatherEvent.OnEnterScreen -> loadCity()
            is CityWeatherEvent.OnSearchCity -> searchCityWeather(cityName = event.cityName)
            is CityWeatherEvent.OnLikeCity -> likeCity(cityName = event.cityName)
        }
    }

    private fun loadCity() {
        _state.value = state.value.copy(
            error = WeatherError.NOT_SELECTED_CITY,
        )
    }

    private fun searchCityWeather(cityName: String) {
        _state.value = state.value.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            val result = getCityWeatherUseCase(cityName = cityName)
            Log.d("mytag", "Пришли данные")
            if (result is Result.Success) {
                Log.d("mytag", "успех")
                _state.value = state.value.copy(
                    isLoading = false,
                    error = null,
                    cityWeatherUiModel = result.data.toCityWeatherUiModel(),
                )
            } else if (result is Result.Error) {
                Log.d("mytag", "ошибка")
                _state.value = state.value.copy(
                    isLoading = false,
                    error = result.error,
                )
            }
        }
    }

    private fun likeCity(cityName: String) {
        viewModelScope.launch {
            likeCityUseCase(
                city = City(
                    name = cityName,
                    isSelected = true,
                ),
            )
        }
    }
}

private fun CityWeather.toCityWeatherUiModel(): CityWeatherUiModel {
    return CityWeatherUiModel(
        name = name,
        description = description,
        temperature = temperature,
        temperatureFeelsLike = temperatureFeelsLike,
        humidity = humidity,
        pressure = pressure,
    )
}