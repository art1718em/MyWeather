package com.example.navigation.destinations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.favourites.api.FavouritesWrapper
import kotlinx.serialization.Serializable

@Serializable
data object FavouritesDestination

fun NavGraphBuilder.favouritesDestination(
    onNavigateBack: () -> Unit,
){
    composable<FavouritesDestination> (
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
        FavouritesWrapper(
            onNavigateBack = onNavigateBack,
        )
    }
}