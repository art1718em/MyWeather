package com.example.search.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.search.ui.state.CityWeatherEvent

@Composable
internal fun SearchBox(
    onEvent: (CityWeatherEvent) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
        )

        TextField(
            modifier = Modifier
                .weight(1f),
            value = text,
            onValueChange = { s ->
                text = s
            }
        )

        Button(
            modifier = Modifier
                .background(Color.Yellow),
            onClick = {
                onEvent(CityWeatherEvent.OnSearchCity(cityName = text))
            }
        ) {
            Text(
                text = "Найти",
            )
        }
    }
}