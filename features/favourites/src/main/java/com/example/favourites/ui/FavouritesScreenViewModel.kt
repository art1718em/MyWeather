package com.example.favourites.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.favourites.domain.usecase.ChangeSelectedCityUseCase
import com.example.favourites.domain.usecase.GetFavouritesCitiesUseCase
import com.example.favourites.domain.usecase.GetFirstCityUseCase
import com.example.favourites.domain.usecase.GetSelectedCityUseCase
import com.example.favourites.ui.state.FavouritesEvent
import com.example.favourites.ui.state.FavouritesScreenState
import com.example.favourites.ui.state.FavouriteCityUIModel
import com.example.likes.api.LikeCenter
import com.example.likes.api.LikeStatus
import com.example.model.CityWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavouritesScreenViewModel @Inject constructor(
    private val getFavouritesCitiesUseCase: GetFavouritesCitiesUseCase,
    private val getSelectedCityUseCase: GetSelectedCityUseCase,
    private val changeSelectedCityUseCase: ChangeSelectedCityUseCase,
    private val getFirstCityUseCase: GetFirstCityUseCase,
    private val likeCenter: LikeCenter,

    ) : ViewModel() {

    private val _state = MutableStateFlow(FavouritesScreenState.default)
    val state = _state.asStateFlow()

    fun obtainEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.OnEnterScreen -> loadFavouritesCities()
            is FavouritesEvent.OnSelectCity -> selectCity(cityName = event.cityName)
            is FavouritesEvent.OnDislikeCity -> dislikeCity(cityName = event.cityName)
        }
    }

    private fun loadFavouritesCities() {
        _state.value = state.value.copy(
            isLoading = true,
        )

        viewModelScope.launch {

            getFavouritesCitiesUseCase().combine(getSelectedCityUseCase()) { citiesWeather, selectedCity ->
                if (selectedCity == null){
                    val firstCity = getFirstCityUseCase()
                    if (firstCity != null){
                        changeSelectedCityUseCase(firstCity)
                    }
                }
                citiesWeather.map { cityWeather ->
                    cityWeather.toFavouritesCityUiModel(cityWeather.name == selectedCity)
                }
            }.collect{
                _state.value = state.value.copy(
                    isLoading = false,
                    error = null,
                    favouritesCities = it,
                )
            }
        }
    }

    private fun selectCity(cityName: String) {
        viewModelScope.launch {
            changeSelectedCityUseCase(cityName = cityName)
        }
    }

    private fun dislikeCity(cityName: String) {
        viewModelScope.launch {
            likeCenter.setLikeStatus(
                LikeStatus(
                    cityName = cityName,
                    isLiked = false,
                )
            )
        }
    }
}

private fun CityWeather.toFavouritesCityUiModel(isSelected: Boolean): FavouriteCityUIModel {
    return FavouriteCityUIModel(
        cityName = name,
        isSelected = isSelected,
    )
}