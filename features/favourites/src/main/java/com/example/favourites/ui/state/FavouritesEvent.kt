package com.example.favourites.ui.state

internal sealed interface FavouritesEvent {
    data object OnEnterScreen : FavouritesEvent
    data class OnSelectCity(val cityName: String) : FavouritesEvent
    data class OnDislikeCity(val cityName: String) : FavouritesEvent
}