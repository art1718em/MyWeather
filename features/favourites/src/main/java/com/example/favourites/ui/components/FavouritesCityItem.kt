package com.example.favourites.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design.components.FavouriteIcon
import com.example.design.theme.MyWeatherTheme
import com.example.favourites.ui.state.FavouriteCityUIModel
import com.example.favourites.ui.state.FavouritesEvent

@Composable
internal fun FavouritesCityItem(
    favouriteCityUIModel: FavouriteCityUIModel,
    onClick: () -> Unit,
    onDislike: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                if (favouriteCityUIModel.isSelected) {
                    MyWeatherTheme.colors.selectedCityBackground
                } else {
                    MyWeatherTheme.colors.background
                }
            )
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = favouriteCityUIModel.cityName,
        )

        IconButton(
            onClick = onDislike,
        ) {
            FavouriteIcon(true)
        }

    }
}
