package com.android.example.music.ui.homefragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.example.music.R
import com.android.example.music.ui.components.Refresher
import com.android.example.music.ui.components.SongRow
import com.android.example.music.ui.theme.NotoSerif

@Composable
fun HomeView(
    homeUiState: HomeUiState,
    onSongClicked: (String, Int) -> Unit,
    onPlayListButtonClicked: (Int) -> Unit,
    onSettingsIconClicked: () -> Unit
) {
    val playList by homeUiState.songsList.collectAsState()
    val isShuffle by homeUiState.isShuffle.collectAsState()
    Refresher(
        isLoadingState = homeUiState.isLoading,
        refresh = homeUiState.refresh
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "HOME PANEL",
                        fontFamily = NotoSerif,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    IconButton(
                        onClick = { onSettingsIconClicked() },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_settings),
                            contentDescription = null,
                        )
                    }
                }
                Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 3.dp)
                Spacer(modifier = Modifier.height(48.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                        .height(48.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Songs List:",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = NotoSerif,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(48.dp))
                    Button(
                        onClick = {onPlayListButtonClicked(homeUiState.playList())},
                        elevation = ButtonDefaults.buttonElevation(10.dp),
                        colors = buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_playlist),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(26.dp))
                    Button(
                        onClick = { homeUiState.onShuffleIconToggled() },
                        elevation = ButtonDefaults.buttonElevation(10.dp),
                        colors = buttonColors(
                            containerColor = if (isShuffle) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                            contentColor = if (isShuffle) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimary,
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shuffle),
                            contentDescription = null,
                            tint = if (isShuffle) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                Divider(
                    color = MaterialTheme.colorScheme.onBackground,
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                LazyColumn{
                    playList.forEachIndexed { index, song ->
                        item {
                            SongRow(
                                song.name,
                                isInPlaylist = song.isInPlaylist,
                                isForSettings = false,
                            ) {
                                onSongClicked(song.name, index)
                                homeUiState.playSong(index)
                            }
                        }
                    }
                }
            }
        }
    }
}
