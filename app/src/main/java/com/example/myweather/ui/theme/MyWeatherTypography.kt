package com.example.myweather.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class MyWeatherTypography(
    val bodyMedium: TextStyle,
)

internal val LocalMyWeatherTypography = staticCompositionLocalOf<MyWeatherTypography> {
    error("No typography provided")
}

internal fun provideMyWeatherTypography(): MyWeatherTypography {
    return MyWeatherTypography(
        bodyMedium = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
    )
}



