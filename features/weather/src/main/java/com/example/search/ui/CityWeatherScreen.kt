package com.example.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.design.components.ErrorScreen
import com.example.design.components.FavouriteIcon
import com.example.design.components.ProgressBar
import com.example.design.theme.MyWeatherTheme
import com.example.features.search.R
import com.example.model.WeatherDescription
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

            cityWeatherUiModel.description?.let { description ->
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = when(description){
                        WeatherDescription.CLEARLY -> "ясно"
                        WeatherDescription.DULL -> "пасмурно"
                        WeatherDescription.SLIGHT_CLOUD_COVER -> "небольшая облачность"
                        WeatherDescription.CLOUDY_WITH_CLARIFICATIONS -> "облачно с прояснениями"
                        WeatherDescription.PARTLY_CLOUDY -> "переменная облачность"
                        WeatherDescription.RAIN -> "дождь"
                        WeatherDescription.LIGHT_RAIN -> "небольшой дождь"
                        WeatherDescription.SNOW -> "снег"
                        WeatherDescription.RAINFALL -> "ливень"
                        WeatherDescription.THUNDERSTORM -> "гроза"
                        WeatherDescription.FOG -> "туман"
                        WeatherDescription.MIST -> "мгла"
                    },
                    style = MyWeatherTheme.typography.bodyMedium,
                )

                AsyncImage(
                    modifier = Modifier
                        .size(80.dp),
                    model = when(description){
                        WeatherDescription.CLEARLY ->
                            "https://www.svgrepo.com/show/71693/sun-bright.svg"
                        WeatherDescription.DULL ->
                            "https://static.vecteezy.com/system/resources/previews/000/441/101/original/cloudy-vector-icon.jpg"
                        WeatherDescription.SLIGHT_CLOUD_COVER ->
                            "https://storage.needpix.com/rsynced_images/clouds-3000994_1280.png"
                        WeatherDescription.CLOUDY_WITH_CLARIFICATIONS ->
                            "https://www.shareicon.net/download/2016/04/25/754970_cloud_512x512.png"
                        WeatherDescription.PARTLY_CLOUDY ->
                            "https://i.pinimg.com/originals/a2/96/a5/a296a54d51db24a20d5b2c905c84414e.jpg"
                        WeatherDescription.RAIN ->
                            "https://i.pinimg.com/originals/d6/73/49/d67349d41604c3602220ac36a5133e42.png"
                        WeatherDescription.LIGHT_RAIN ->
                            "https://i.pinimg.com/736x/ea/45/ea/ea45ea1e4b06283740b4effd5c12fc4d.jpg"
                        WeatherDescription.SNOW ->
                            "https://static.vecteezy.com/system/resources/previews/004/999/451/large_2x/snowflake-icon-on-grey-background-free-vector.jpg"
                        WeatherDescription.RAINFALL ->
                            "https://www.pngkit.com/png/detail/5-59432_snow-keep-your-passwords-safe.png"
                        WeatherDescription.THUNDERSTORM ->
                            "https://www.pngkit.com/png/detail/438-4382250_cloud-lightening-rain-storm-comments-icon.png"
                        WeatherDescription.FOG ->
                            "https://cdn0.iconfinder.com/data/icons/weather-151/512/Wheater-30-512.png"
                        WeatherDescription.MIST ->
                            "https://banner2.cleanpng.com/20180508/ede/kisspng-drawing-5af1db87e2a932.1204255615257998159284.jpg"
                    },
                    contentDescription = null,
                )
            }

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

            val currentContext = LocalContext.current
            Button(
                onClick = {
                    onEvent(CityWeatherEvent.OnSendNotification(currentContext))
                }
            ) {
                Text(
                    text = "Получить сообщение!"
                )
            }
        }
    }
}