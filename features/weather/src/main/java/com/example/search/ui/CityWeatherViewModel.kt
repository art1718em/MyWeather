package com.example.search.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.likes.api.LikeCenter
import com.example.likes.api.LikeInteractor
import com.example.likes.api.LikeStatus
import com.example.model.CityWeather
import com.example.model.Result
import com.example.search.domain.model.WeatherError
import com.example.search.domain.usecase.GetCityWeatherUseCase
import com.example.search.domain.usecase.GetSelectedCityUseCase
import com.example.search.domain.usecase.IsCityExistUseCase
import com.example.search.domain.usecase.UnselectCityUseCase
import com.example.search.ui.state.CityWeatherEvent
import com.example.search.ui.state.CityWeatherScreenState
import com.example.search.ui.state.CityWeatherUiModel
import com.example.workmanager.ToastWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
internal class CityWeatherViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val getSelectedCityUseCase: GetSelectedCityUseCase,
    private val isCityExistUseCase: IsCityExistUseCase,
    private val unselectCityUseCase: UnselectCityUseCase,
    private val likeCenter: LikeCenter,
    private val likeInteractor: LikeInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow(CityWeatherScreenState.default)
    val state = _state.asStateFlow()

    var collectLikeJob: Job? = null

    fun obtainEvent(event: CityWeatherEvent) {
        when (event) {
            is CityWeatherEvent.OnEnterScreen -> loadCity()
            is CityWeatherEvent.OnSearchCity -> searchCityWeather(cityName = event.cityName)
            is CityWeatherEvent.OnLikeCity -> changeCityLikesState(cityName = event.cityName)
            is CityWeatherEvent.OnSendNotification -> showToast(context = event.context)
        }
    }

    private fun loadCity() {
        if (state.value.cityWeatherUiModel == CityWeatherUiModel.default) {
            _state.value = state.value.copy(
                isLoading = true,
            )

            viewModelScope.launch {
                getSelectedCityUseCase().collect { selectedCity ->
                    if (selectedCity == null) {
                        if (state.value.cityWeatherUiModel == CityWeatherUiModel.default) {
                            _state.value = state.value.copy(
                                isLoading = false,
                                error = WeatherError.NOT_SELECTED_CITY,
                            )
                        }
                    } else {
                        searchCityWeather(cityName = selectedCity)
                    }
                }
            }
        }
    }

    private fun searchCityWeather(cityName: String) {
        _state.value = state.value.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            val resultCityWeather = getCityWeatherUseCase(cityName = cityName)
            val isCityExist = isCityExistUseCase(cityName = cityName)

            if (resultCityWeather is Result.Success) {
                if (!isCityExist){
                    unselectCityUseCase()

                }

                _state.value = state.value.copy(
                    isLoading = false,
                    error = null,
                    cityWeatherUiModel = resultCityWeather.data.toCityWeatherUiModel(
                        isLiked = isCityExist,
                    ),
                )

                collectLikeJob?.cancel()

                collectLikeJob = viewModelScope.launch {
                    likeInteractor.getLikeCityFlow(state.value.cityWeatherUiModel.name).collect {
                        _state.value = state.value.copy(
                            cityWeatherUiModel = state.value.cityWeatherUiModel.copy(
                                isLiked = it,
                            ),
                        )
                    }
                }

            } else if (resultCityWeather is Result.Error) {
                _state.value = state.value.copy(
                    isLoading = false,
                    error = resultCityWeather.error,
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

    private fun showToast(context: Context){
        Toast.makeText(context, "Через 5 секунд вы получите сообщение!", Toast.LENGTH_LONG).show()
        val workRequest = OneTimeWorkRequestBuilder<ToastWorker>()
            .addTag("work")
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
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