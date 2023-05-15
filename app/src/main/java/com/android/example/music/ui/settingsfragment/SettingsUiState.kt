package com.android.example.music.ui.settingsfragment

import com.android.example.music.models.Song
import kotlinx.coroutines.flow.StateFlow

data class SettingsUiState (
    val songList: StateFlow<List<Song>>,
    val isLoading: StateFlow<Boolean>,
    val refresh: () -> Unit
)
