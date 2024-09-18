package com.example.navigation.destinations

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.search.api.CityWeatherWrapper
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherDestination(val cityName: String)

fun NavGraphBuilder.cityWeatherDestination(
    navigateToFavourites: () -> Unit,
) {
    composable(
        route = "cityWeather/{cityName}",
        arguments = listOf(navArgument("cityName") { type = NavType.StringType }),
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700))
        }
    ) { navBackStackEntry ->
        val cityName = navBackStackEntry.arguments?.getString("cityName") ?: "DefaultCity"
        CityWeatherWrapper(cityName = cityName)
    }
}
