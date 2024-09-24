package com.example.favourites.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.example.favourites.ui.components.FavouritesCityItem
import com.example.favourites.ui.state.FavouritesEvent
import com.example.favourites.ui.state.FavouritesScreenState

@Composable
internal fun FavouritesScreen(
    state: FavouritesScreenState,
    onEvent: (FavouritesEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        items(state.favouritesCities) { favouriteCity ->
            FavouritesCityItem(
                favouriteCityUIModel = favouriteCity,
                onClick = {
                    onEvent(FavouritesEvent.OnSelectCity(favouriteCity.cityName))
                },
                onDislike = {
                    onEvent(FavouritesEvent.OnDislikeCity(favouriteCity.cityName))
                },
            )
        }
    }
}