package com.android.example.music.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColorScheme(
    primary = Teal200,
    inversePrimary = Pink200,
    background = Blue500,
    onBackground = White
)

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF325CA8),
    inversePrimary = Pink200,
    background = White,
    onBackground = Blue500
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

