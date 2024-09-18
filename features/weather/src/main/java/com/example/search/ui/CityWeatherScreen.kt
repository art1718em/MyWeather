package com.example.search.ui

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
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
){


    if (cityWeatherScreenState.isLoading){

    } else if (cityWeatherScreenState.error != null){
        if (cityWeatherScreenState.error == WeatherError.NOT_SELECTED_CITY){
            SearchBox()
        }
    } else {
        CityWeatherScreenContent(
            cityWeatherUiModel = cityWeatherScreenState.cityWeatherUiModel,
        )
    }

}


@Composable
private fun CityWeatherScreenContent(
    cityWeatherUiModel: CityWeatherUiModel,
){
    SearchBox()
}