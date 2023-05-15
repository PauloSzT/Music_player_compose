package com.android.example.music.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFAEC6FF),
    inversePrimary = Color(0xFF325CA8),
    background = Color(0xFF1B1B1F),
    onBackground = Color(0xFFE3E2E6),
    onPrimaryContainer = Color(0xFFD8E2FF),
    onSecondary = Color(0xFF00363D)
)

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF325CA8),
    inversePrimary = Color(0xFFAEC6FF),
    background = Color(0xFFFEFBFF),
    onBackground = Color(0xFF1B1B1F),
    onPrimaryContainer = Color(0xFF001A42),
    onSecondary = Color(0xFFFFFFFF),
)

@Composable
fun MusicAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if(darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
