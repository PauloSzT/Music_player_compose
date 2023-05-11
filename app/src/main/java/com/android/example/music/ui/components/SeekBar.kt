package com.android.example.music.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SeekBarDemo() {
    // Create a mutable state for the SeekBar value
    val seekBarValue = remember { mutableStateOf(0f) }

    // Set the range for the SeekBar values (0 to 100 in this case)
    val seekBarRange = 0f..100f

    Slider(
        value = seekBarValue.value,
        onValueChange = { newValue ->
            seekBarValue.value = newValue
        },
        valueRange = seekBarRange,
        modifier = Modifier.width(200.dp),
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.inversePrimary,
            activeTrackColor = Color.Black,
            inactiveTrackColor = Color.White
        )
    )
}