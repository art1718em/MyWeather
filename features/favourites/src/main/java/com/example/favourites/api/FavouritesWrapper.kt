package com.example.favourites.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.favourites.ui.FavouritesScreen
import com.example.favourites.ui.FavouritesScreenViewModel
import com.example.favourites.ui.state.FavouritesEvent

@Composable
fun FavouritesWrapper(
    onNavigateBack: () -> Unit,
){
    val favouritesScreenViewModel = hiltViewModel<FavouritesScreenViewModel>()

    val state by favouritesScreenViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        favouritesScreenViewModel.obtainEvent(event = FavouritesEvent.OnEnterScreen)
    }

    FavouritesScreen(
        state = state,
        onEvent = favouritesScreenViewModel::obtainEvent,
        onNavigateBack = onNavigateBack,
    )
}