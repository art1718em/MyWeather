package com.example.search.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.search.ui.CityWeatherScreen
import com.example.search.ui.CityWeatherViewModel
import com.example.search.ui.state.CityWeatherEvent

@Composable
fun CityWeatherWrapper(cityName: String){
    val cityWeatherViewModel = hiltViewModel<CityWeatherViewModel>()

    val state by cityWeatherViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (cityName == "DefaultCity"){
            cityWeatherViewModel.obtainEvent(
                CityWeatherEvent.OnEnterScreen
            )
        } else {
            cityWeatherViewModel.obtainEvent(
                CityWeatherEvent.OnSearchCity(cityName)
            )
        }
    }

    CityWeatherScreen(
        cityWeatherScreenState = state,
        onEvent = cityWeatherViewModel::obtainEvent,
    )
}