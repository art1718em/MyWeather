package com.example.search.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.search.ui.CityWeatherScreen
import com.example.search.ui.CityWeatherViewModel
import com.example.search.ui.state.CityWeatherEvent

@Composable
fun CityWeatherWrapper(
    navigateToFavourites: () -> Unit,
){
    val cityWeatherViewModel = hiltViewModel<CityWeatherViewModel>()

    val state by cityWeatherViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        cityWeatherViewModel.obtainEvent(
            CityWeatherEvent.OnEnterScreen
        )
    }

    CityWeatherScreen(
        cityWeatherScreenState = state,
        onEvent = cityWeatherViewModel::obtainEvent,
        onNavigateToFavourites = navigateToFavourites,
    )
}