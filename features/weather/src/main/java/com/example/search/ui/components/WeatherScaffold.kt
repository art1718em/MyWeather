package com.example.search.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.design.theme.MyWeatherTheme

@Composable
internal fun WeatherScaffold(
    onActionButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
){
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onActionButtonClick
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                )
            }
        },
        containerColor = MyWeatherTheme.colors.background,
    ) { paddingValues ->
        content(paddingValues)
    }
}
