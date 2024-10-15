package com.example.search.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.design.components.MyWeatherButton
import com.example.design.theme.MyWeatherTheme
import com.example.features.search.R
import com.example.search.ui.state.CityWeatherEvent

@Composable
internal fun SearchBox(
    onEvent: (CityWeatherEvent) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 4.dp,
                color = MyWeatherTheme.colors.searchBorder,
                shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 8.dp),
            imageVector = Icons.Default.Search,
            contentDescription = null,
        )

        TextField(
            modifier = Modifier
                .weight(1f),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MyWeatherTheme.colors.background,
                focusedContainerColor = MyWeatherTheme.colors.background,
                cursorColor = MyWeatherTheme.colors.mainText,
                focusedIndicatorColor = MyWeatherTheme.colors.background,
                unfocusedIndicatorColor = MyWeatherTheme.colors.background,
            ),
            value = text,
            onValueChange = { newValue ->
                text = newValue
            }
        )

       MyWeatherButton(
           onClick = {
               onEvent(CityWeatherEvent.OnSearchCity(cityName = text))
           },
           text = stringResource(R.string.search),
       )
    }
}