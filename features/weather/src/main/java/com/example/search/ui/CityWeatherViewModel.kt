package com.example.search.ui

import androidx.lifecycle.ViewModel
import com.example.search.domain.usecase.GetCityWeatherUseCase
import com.example.search.ui.state.CityWeatherEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
) : ViewModel() {

    fun obtainEvent(event: CityWeatherEvent) {
        when(event){
            is CityWeatherEvent.OnEnterScreen -> loadCity()
            is CityWeatherEvent.OnSearchCity -> searchCityWeather()
            is CityWeatherEvent.OnEditSearch -> editSearch(cityName = event.cityName)
        }
    }

    private fun loadCity(){

    }

    private fun searchCityWeather(){

    }

    private fun editSearch(cityName: String){

    }
}