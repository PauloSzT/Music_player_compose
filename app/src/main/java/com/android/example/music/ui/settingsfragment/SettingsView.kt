package com.android.example.music.ui.settingsfragment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.example.music.ui.components.SongRow
import com.android.example.music.ui.theme.NotoSerif

@Composable
fun SettingsView(
    settingsUiState: SettingsUiState,
    onSongClicked: (Int) -> Unit,
) {
    val songsList by settingsUiState.songList.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = "SETTINGS",
                fontFamily = NotoSerif,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            Divider(color = MaterialTheme.colorScheme.primary, thickness = 3.dp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                text = "Songs List",
                color = MaterialTheme.colorScheme.primary,
                fontFamily = NotoSerif,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
            LazyColumn(
            ) {
                songsList.forEach { song ->
                    item {
                        SongRow(
                            song.name,
                            song.index,
                            isInPlaylist = song.isInPlaylist,
                            isForSettings = true
                        ) { onSongClicked(song.index) }
                    }
                }
            }
        }
    }
}
