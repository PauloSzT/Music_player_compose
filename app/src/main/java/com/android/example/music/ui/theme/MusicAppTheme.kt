package com.android.example.music.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFAEC6FF),
    inversePrimary = Color(0xFF325CA8),
    background = Color(0xFF1B1B1F),
    onBackground = Color(0xFFE3E2E6),
    primaryContainer =Color(0xFF12448F),
    onPrimaryContainer = Color(0xFFD8E2FF),
    secondaryContainer = Color(0xFF4FD8EB),
    onSecondaryContainer = Color(0xFF97F0FF),
    secondary = Color(0xFF4FD8EB),
    onSecondary = Color(0xFF00363D)
)

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF325CA8),
    inversePrimary = Color(0xFFAEC6FF),
    background = Color(0xFFFEFBFF),
    onBackground = Color(0xFF1B1B1F),
    primaryContainer =Color(0xFFD8E2FF),
    onPrimaryContainer = Color(0xFF001A42),
    secondaryContainer = Color(0xFF006874),
    onSecondaryContainer = Color(0xFF001F24),
    secondary = Color(0xFF006874),
    onSecondary = Color(0xFFFFFFFF),
)


@Composable
fun MusicAppTheme(
    isDynamicColor: Boolean = true,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {

    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colors = if (darkTheme) {
        if (dynamicColor) {
            dynamicDarkColorScheme(LocalContext.current)
        } else {
            DarkColorPalette
        }
    } else {
        if (dynamicColor) {
            dynamicLightColorScheme(LocalContext.current)
        } else {
            LightColorPalette
        }
    }
    MaterialTheme(
        colorScheme = colors, typography = Typography, content = content
    )
}
