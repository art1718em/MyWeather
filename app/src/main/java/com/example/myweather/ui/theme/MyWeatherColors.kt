package com.example.myweather.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class MyWeatherColors(
    val black: Color,
)

internal val LocalMyWeatherColors = staticCompositionLocalOf<MyWeatherColors> {
    error("No colors provided")
}

internal val lightColorScheme = MyWeatherColors(
    black = Color(color = 0xFF000000),
)

internal val darkColorScheme = MyWeatherColors(
    black = Color(color = 0xFF000000),
)
