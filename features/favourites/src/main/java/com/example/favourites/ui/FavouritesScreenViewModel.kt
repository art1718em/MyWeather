package com.example.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favourites.domain.usecase.GetFavouritesCitiesUseCase
import com.example.favourites.ui.state.FavouritesEvent
import com.example.favourites.ui.state.FavouritesScreenState
import com.example.favourites.ui.state.FavouriteCityUIModel
import com.example.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavouritesScreenViewModel @Inject constructor(
    private val getFavouritesCitiesUseCase: GetFavouritesCitiesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavouritesScreenState.default)
    val state = _state.asStateFlow()

    fun obtainEvent(event: FavouritesEvent){
        when(event){
            is FavouritesEvent.OnEnterScreen -> loadFavouritesCities()
            is FavouritesEvent.OnSelectCity -> selectCity(cityName = event.cityName)
            is FavouritesEvent.OnDislikeCity -> dislikeCity(cityName = event.cityName)
        }
    }

    private fun loadFavouritesCities(){
        _state.value = state.value.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            getFavouritesCitiesUseCase().collect{ cities ->
                _state.value = state.value.copy(
                    isLoading = false,
                    favouritesCities = cities.map { it.toFavouritesCityUiModel() },
                )
            }
        }
    }

    private fun selectCity(cityName: String){

    }

    private fun dislikeCity(cityName: String){

    }
}

private fun City.toFavouritesCityUiModel(): FavouriteCityUIModel{
    return FavouriteCityUIModel(
        cityName = name,
        isSelected = isSelected,
    )
}