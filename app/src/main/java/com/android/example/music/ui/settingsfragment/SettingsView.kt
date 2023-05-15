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
import com.android.example.music.ui.components.Refresher
import com.android.example.music.ui.components.SongRow
import com.android.example.music.ui.theme.NotoSerif

@Composable
fun SettingsView(
    settingsUiState: SettingsUiState,
) {
    val songsList by settingsUiState.songList.collectAsState()
    Refresher(
        isLoadingState = settingsUiState.isLoading,
        refresh = settingsUiState.refresh
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
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
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, start = 8.dp),
                    text = "Songs List:",
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = NotoSerif,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
                LazyColumn{
                    songsList.forEachIndexed { index, song ->
                        item {
                            SongRow(
                                song.name,
                                isInPlaylist = song.isInPlaylist,
                                isForSettings = true
                            ) { settingsUiState.addOrRemove(index) }
                        }
                    }
                }
            }
        }
    }
}
