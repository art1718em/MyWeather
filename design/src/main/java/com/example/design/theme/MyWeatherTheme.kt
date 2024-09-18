package com.example.design.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object MyWeatherTheme {
    val colors: MyWeatherColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMyWeatherColors.current

    val typography: MyWeatherTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMyWeatherTypography.current
}

@Composable
fun MyWeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    val typography = provideMyWeatherTypography()

    CompositionLocalProvider(
        LocalMyWeatherColors provides colors,
        LocalMyWeatherTypography provides typography,
        content = content,
    )
}
