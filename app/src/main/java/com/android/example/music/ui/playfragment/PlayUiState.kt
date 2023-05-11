package com.android.example.music.ui.playfragment

import kotlinx.coroutines.flow.StateFlow

data class PlayUiState(val seekbarPosition: StateFlow<Pair<Float,Float>>, val onSeekbarChange: (Float) -> Unit)
