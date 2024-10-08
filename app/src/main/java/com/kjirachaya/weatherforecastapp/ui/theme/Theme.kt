package com.kjirachaya.weatherforecastapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme =
    lightColorScheme(
        primary = copperOrange,
        secondary = goldenrod,
        surface = darkCopper,
        error = red85,
        primaryContainer = white42,
        secondaryContainer = white63,
        onSurface = Color.White,
    )

@Composable
fun WeatherForecastAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
    )
}
