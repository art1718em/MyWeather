package com.example.favourites.ui.state

internal data class FavouriteCityUIModel(
    val cityName: String,
    val isSelected: Boolean,
){
    companion object{
        val default = FavouriteCityUIModel(
            cityName = "",
            isSelected = false,
        )
    }
}