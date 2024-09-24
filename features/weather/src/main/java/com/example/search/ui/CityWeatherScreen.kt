package com.example.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                SearchBox(onEvent = onEvent)
                Text("Введите город или выберите из избранного")
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
    ) {
        SearchBox(
            onEvent = onEvent,
        )

        Text(text = "Город " + cityWeatherUiModel.name)
        Text(text = "Описание " + cityWeatherUiModel.description)
        Text(text = "Темература " + cityWeatherUiModel.temperature)
        Text(text = "Температура ощущается " + cityWeatherUiModel.temperatureFeelsLike)
        Text(text = "Давление " + cityWeatherUiModel.humidity)
        Text(text = "Влажность " + cityWeatherUiModel.pressure)

        Button(
            onClick = {
                onEvent(CityWeatherEvent.OnLikeCity(cityName = cityWeatherUiModel.name))
            }
        ){
            Text(text = "Добавить в избранное")
        }

        Button(
            onClick = navigateToFavourites,
        ){
            Text(text = "Перейти в избранное")
        }
    }
}