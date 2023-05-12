package com.android.example.music.models

import kotlinx.coroutines.flow.MutableStateFlow

class Song(
    val path: String,
    var index: Int,
    val name: String,
    var isInPlaylist : MutableStateFlow<Boolean> = MutableStateFlow(false)
)
