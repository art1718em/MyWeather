package com.example.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.likes.api.LikeCenter
import com.example.likes.api.LikeInteractor
import com.example.likes.api.LikeStatus
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CityWeatherViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val getSelectedCityUseCase: GetSelectedCityUseCase,
    private val likeCityUseCase: LikeCityUseCase,
    private val likeCenter: LikeCenter,
    private val likeInteractor: LikeInteractor,
) : ViewModel() {

    init{
        Log.d("mytag1", "create vm")
    }

    private val _state = MutableStateFlow(CityWeatherScreenState.default)
    val state = _state.asStateFlow()

    var collectLikeJob: Job? = null

    fun obtainEvent(event: CityWeatherEvent) {
        when (event) {
            is CityWeatherEvent.OnEnterScreen -> loadCity()
            is CityWeatherEvent.OnSearchCity -> searchCityWeather(cityName = event.cityName)
            is CityWeatherEvent.OnLikeCity -> changeCityLikesState(cityName = event.cityName)
        }
    }

    private fun loadCity() {
        if (state.value.cityWeatherUiModel == CityWeatherUiModel.default){
            _state.value = state.value.copy(
                error = WeatherError.NOT_SELECTED_CITY,
            )
        }
    }

    private fun searchCityWeather(cityName: String) {
        _state.value = state.value.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            val result = getCityWeatherUseCase(cityName = cityName)
            if (result is Result.Success) {
                _state.value = state.value.copy(
                    isLoading = false,
                    error = null,
                    cityWeatherUiModel = result.data.toCityWeatherUiModel(true),
                )

                collectLikeJob?.cancel()

                collectLikeJob = viewModelScope.launch {
                    likeInteractor.getLikeCityFlow(state.value.cityWeatherUiModel.name).collect{
                        _state.value = state.value.copy(
                            cityWeatherUiModel = state.value.cityWeatherUiModel.copy(
                                isLiked = it
                            )
                        )
                    }
                }

            } else if (result is Result.Error) {
                _state.value = state.value.copy(
                    isLoading = false,
                    error = result.error,
                )
            }
        }
    }

    private fun changeCityLikesState(cityName: String) {
        viewModelScope.launch {
            likeCenter.setLikeStatus(
                LikeStatus(cityName = cityName, isLiked = !state.value.cityWeatherUiModel.isLiked)
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("mytag1", "clear vm")
    }
}

private fun CityWeather.toCityWeatherUiModel(isLiked: Boolean): CityWeatherUiModel {
    return CityWeatherUiModel(
        name = name,
        description = description,
        temperature = temperature,
        temperatureFeelsLike = temperatureFeelsLike,
        humidity = humidity,
        pressure = pressure,
        isLiked = isLiked,
    )
}