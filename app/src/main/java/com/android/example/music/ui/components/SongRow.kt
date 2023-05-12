package com.android.example.music.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.example.music.R
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SongRow(
    songName: String,
    songIndex: Int,
    isSelected: MutableStateFlow<Boolean>,
    isForSettings: Boolean,
    onSongClicked: (Int) -> Unit
) {
    val isInPlaylist by isSelected.collectAsState()
    Row(modifier = Modifier
        .clickable { onSongClicked(songIndex) }
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .background(
            if (isInPlaylist && isForSettings) Color.Cyan else if (isForSettings) Color.LightGray else Color.Transparent)) {
        Icon(
            painter = painterResource(R.drawable.ic_music),
            contentDescription = null,
        )
        Text(
            text = songName,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
