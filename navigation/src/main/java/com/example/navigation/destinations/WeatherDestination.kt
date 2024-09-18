package com.example.navigation.destinations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.search.api.CityWeatherWrapper
import kotlinx.serialization.Serializable

@Serializable
data object WeatherDestination

fun NavGraphBuilder.weatherDestination(
    navigateBack: () -> Unit,
){
    composable<CityWeatherDestination> (
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(durationMillis = 700)
            )
        },
        popExitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(durationMillis = 700)
            )
        },
    ){

    }
}