package com.example.design.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class MyWeatherColors(
    val background: Color,
)

internal val LocalMyWeatherColors = staticCompositionLocalOf<MyWeatherColors> {
    error("No colors provided")
}

internal val lightColorScheme = MyWeatherColors(
    background = Color(color = 0xFFFFFFFF),
)

internal val darkColorScheme = MyWeatherColors(
    background = Color(color = 0xFFFFFFFF),
)
