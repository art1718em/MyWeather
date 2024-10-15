package com.example.design.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class MyWeatherTypography(
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
)

internal val LocalMyWeatherTypography = staticCompositionLocalOf<MyWeatherTypography> {
    error("No typography provided")
}

internal fun provideMyWeatherTypography(): MyWeatherTypography {
    return MyWeatherTypography(
        bodyLarge = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodyMedium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
        )
    )
}



