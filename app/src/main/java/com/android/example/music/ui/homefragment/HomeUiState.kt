package com.android.example.music.ui.homefragment

import com.android.example.music.models.Song
import kotlinx.coroutines.flow.StateFlow
import java.lang.Thread.State

data class HomeUiState(
    val songsList: StateFlow<List<Song>>,
    val isShuffle: StateFlow<Boolean>,
    val isLoading: StateFlow<Boolean>,
    val onShuffleIconToggled: () -> Unit,
    val refresh: () -> Unit
)
