package com.example.navigation.destinations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.search.api.CityWeatherWrapper
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherDestination(val cityName: String?)

fun NavGraphBuilder.cityWeatherDestination(
    navigateToFavourites: () -> Unit,
){
    composable<CityWeatherDestination> (
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
            )
        },
        popExitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
            )
        },
    ){navBackStackEntry ->
        val cityName = navBackStackEntry.toRoute<CityWeatherDestination>().cityName
        CityWeatherWrapper(cityName = cityName)
    }
}