package com.android.example.music.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.example.music.ui.playfragment.PlayUiState

@Composable
fun MusicSeekbar(
    playUiState: PlayUiState,
) {
    val seekBarValue by playUiState.seekbarPosition.collectAsState()
    val seekBarRange = 0f..seekBarValue.second

    Slider(
        value = seekBarValue.first,
        onValueChange = { newPosition ->
            playUiState.onSeekbarChange(newPosition)
        },
        valueRange = seekBarRange,
        modifier = Modifier.width(200.dp),
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.inversePrimary,
            activeTrackColor = MaterialTheme.colorScheme.onSecondary,
            inactiveTrackColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}
