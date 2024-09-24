package com.example.navigation.destinations

import android.os.Parcelable
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.search.api.CityWeatherWrapper
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data object CityWeatherDestination

fun NavGraphBuilder.cityWeatherDestination(
    navigateToFavourites: () -> Unit,
) {
    composable<CityWeatherDestination>(
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
    ) {
        CityWeatherWrapper(
            navigateToFavourites = navigateToFavourites,
        )
    }
}
