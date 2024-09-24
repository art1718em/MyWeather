package com.example.design.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class MyWeatherColors(
    val background: Color,
    val filledLike: Color,
    val selectedCityBackground: Color,
    val mainText: Color,
    val searchBorder: Color,
    val button: Color,
)

internal val LocalMyWeatherColors = staticCompositionLocalOf<MyWeatherColors> {
    error("No colors provided")
}

internal val lightColorScheme = MyWeatherColors(
    background = Color(color = 0xFFFFFFFF),
    filledLike = Color(color = 0xFFFF0000),
    selectedCityBackground = Color(color = 0xFFFFFFF1),
    mainText = Color(color = 0xFF000000),
    searchBorder = Color(color = 0xFF000000).copy(alpha = 0.5f),
    button = Color(color = 0xFFFFFF33),
)

internal val darkColorScheme = MyWeatherColors(
    background = Color(color = 0xFFFFFFFF),
    filledLike = Color(color = 0xFFFF0000),
    selectedCityBackground = Color(color = 0xFFFFFFF1),
    mainText = Color(color = 0xFF000000),
    searchBorder = Color(color = 0xFF000000).copy(alpha = 0.5f),
    button = Color(color = 0xFFFFFF33),
)
