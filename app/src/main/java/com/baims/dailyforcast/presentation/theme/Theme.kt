package com.baims.dailyforcast.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = lightColorScheme(
    primary = blue,
)

@Composable
fun DailyForecastTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}