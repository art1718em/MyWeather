package com.example.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design.components.FavouriteIcon
import com.example.design.theme.MyWeatherTheme
import com.example.search.domain.model.WeatherError
import com.example.search.ui.components.SearchBox
import com.example.search.ui.state.CityWeatherEvent
import com.example.search.ui.state.CityWeatherScreenState
import com.example.search.ui.state.CityWeatherUiModel

@Composable
internal fun CityWeatherScreen(
    cityWeatherScreenState: CityWeatherScreenState,
    onEvent: (CityWeatherEvent) -> Unit,
    navigateToFavourites: () -> Unit,
) {


    if (cityWeatherScreenState.isLoading) {

    } else if (cityWeatherScreenState.error != null) {
        if (cityWeatherScreenState.error == WeatherError.NOT_SELECTED_CITY) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = navigateToFavourites
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                        )
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it)
                        .padding(8.dp),
                ) {
                    SearchBox(onEvent = onEvent)
                    Text("Введите город или выберите из избранного")
                }
            }

        }
    } else {
        CityWeatherScreenContent(
            cityWeatherUiModel = cityWeatherScreenState.cityWeatherUiModel,
            onEvent = onEvent,
            navigateToFavourites = navigateToFavourites,
        )
    }

}


@Composable
private fun CityWeatherScreenContent(
    cityWeatherUiModel: CityWeatherUiModel,
    onEvent: (CityWeatherEvent) -> Unit,
    navigateToFavourites: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToFavourites
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchBox(
                onEvent = onEvent,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = cityWeatherUiModel.name,
                    style = MyWeatherTheme.typography.bodyLarge,
                )

                FavouriteIcon(
                    isFavourite = cityWeatherUiModel.isLiked,
                )
            }

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.description!!,
                style = MyWeatherTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.temperature.toString() + "℃",
                style = MyWeatherTheme.typography.bodyLarge,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Ощущается как " + cityWeatherUiModel.temperatureFeelsLike + "℃",
                style = MyWeatherTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.humidity.toString() + "%",
                style = MyWeatherTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.pressure.toString() + " мм рт. ст.",
                style = MyWeatherTheme.typography.bodyMedium,
            )

            IconButton(
                onClick = {
                    onEvent(CityWeatherEvent.OnLikeCity(cityWeatherUiModel.name))
                }
            ) {
                FavouriteIcon(cityWeatherUiModel.isLiked)
            }
        }
    }
}