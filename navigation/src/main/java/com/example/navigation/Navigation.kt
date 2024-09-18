package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.navigation.destinations.CityWeatherDestination
import com.example.navigation.destinations.WeatherDestination
import com.example.navigation.destinations.cityWeatherDestination

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CityWeatherDestination,
    ){
        cityWeatherDestination (
                navigateToFavourites = {
                    navController.navigate(WeatherDestination)
                },
        )
    }
}