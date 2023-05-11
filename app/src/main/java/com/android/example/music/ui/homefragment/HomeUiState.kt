package com.android.example.music.ui.homefragment

import com.android.example.music.models.Song
import kotlinx.coroutines.flow.StateFlow

data class HomeUiState(val playList:StateFlow<List<Song>>)
