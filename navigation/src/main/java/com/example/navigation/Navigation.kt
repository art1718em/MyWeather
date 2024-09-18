package com.example.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.navigation.destinations.CityWeatherDestination
import com.example.navigation.destinations.FavouritesDestination
import com.example.navigation.destinations.cityWeatherDestination
import com.example.navigation.destinations.favouritesDestination

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "cityWeather/DefaultCity" // Provide a default value
    ) {
        cityWeatherDestination(
            navigateToFavourites = {
                navController.navigate("favourites")
            }
        )

        // Other destinations like favourites
    }
}