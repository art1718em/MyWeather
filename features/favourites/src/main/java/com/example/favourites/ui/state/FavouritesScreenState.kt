package com.example.favourites.ui.state

import com.example.model.RootError

internal data class FavouritesScreenState(
    val favouritesCities: List<FavouriteCityUIModel>,
    val isLoading: Boolean,
    val error: RootError?,
){
    companion object{
        val default = FavouritesScreenState(
            favouritesCities = emptyList(),
            isLoading = false,
            error = null,
        )
    }
}