package com.example.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.design.components.ErrorScreen
import com.example.design.components.FavouriteIcon
import com.example.design.components.ProgressBar
import com.example.design.theme.MyWeatherTheme
import com.example.features.search.R
import com.example.search.domain.model.WeatherError
import com.example.search.ui.components.SearchBox
import com.example.search.ui.components.WeatherScaffold
import com.example.search.ui.state.CityWeatherEvent
import com.example.search.ui.state.CityWeatherScreenState
import com.example.search.ui.state.CityWeatherUiModel

@Composable
internal fun CityWeatherScreen(
    cityWeatherScreenState: CityWeatherScreenState,
    onEvent: (CityWeatherEvent) -> Unit,
    onNavigateToFavourites: () -> Unit,
) {
    if (cityWeatherScreenState.isLoading) {
        ProgressBar(
            modifier = Modifier
                .fillMaxSize(),
        )
    } else if (cityWeatherScreenState.error != null) {
        if (cityWeatherScreenState.error == WeatherError.NOT_SELECTED_CITY) {
            WeatherScaffold(
                onActionButtonClick = onNavigateToFavourites,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it)
                        .padding(8.dp),
                ) {
                    SearchBox(onEvent = onEvent)

                    Text(stringResource(R.string.input_city_or_choose_from_favourites))
                }
            }
        } else {
            ErrorScreen(
                onUpdate = {
                    onEvent(CityWeatherEvent.OnEnterScreen)
                },
            )
        }
    } else {
        CityWeatherScreenContent(
            cityWeatherUiModel = cityWeatherScreenState.cityWeatherUiModel,
            onEvent = onEvent,
            navigateToFavourites = onNavigateToFavourites,
        )
    }

}


@Composable
private fun CityWeatherScreenContent(
    cityWeatherUiModel: CityWeatherUiModel,
    onEvent: (CityWeatherEvent) -> Unit,
    navigateToFavourites: () -> Unit,
) {
    WeatherScaffold(
        onActionButtonClick = navigateToFavourites,
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

            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = cityWeatherUiModel.name,
                style = MyWeatherTheme.typography.bodyLarge,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.description!!,
                style = MyWeatherTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.temperature.toString() + stringResource(R.string.celsius_icon),
                style = MyWeatherTheme.typography.bodyLarge,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = stringResource(R.string.feels_like) +
                        cityWeatherUiModel.temperatureFeelsLike + stringResource(R.string.celsius_icon),
                style = MyWeatherTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.humidity.toString() + stringResource(R.string.percent),
                style = MyWeatherTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = cityWeatherUiModel.pressure.toString() + stringResource(R.string.millimeters_of_mercury),
                style = MyWeatherTheme.typography.bodyMedium,
            )

            IconButton(
                onClick = { onEvent(CityWeatherEvent.OnLikeCity(cityWeatherUiModel.name)) },
            ) {
                FavouriteIcon(cityWeatherUiModel.isLiked)
            }
        }
    }
}