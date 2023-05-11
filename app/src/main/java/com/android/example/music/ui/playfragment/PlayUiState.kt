package com.android.example.music.ui.playfragment

import com.android.example.music.models.Song
import kotlinx.coroutines.flow.StateFlow

data class PlayUiState(
    val song: StateFlow<Song?>,
    val isPlaying: StateFlow<Boolean>,
    val seekbarPosition: StateFlow<Pair<Float, Float>>,
    val onSeekbarChange: (Float) -> Unit
)
