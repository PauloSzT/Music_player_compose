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
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.example.music.R
import com.android.example.music.ui.components.SongRow
import com.android.example.music.ui.theme.NotoSerif

@Composable
fun HomeView(
    homeUiState: HomeUiState,
    onSongClicked: (String, Int) -> Unit,
    onPlayListButtonClicked: () -> Unit,
    onSettingsIconClicked: () -> Unit,
    onShuffleIconToggled: () -> Unit
) {
    val playList by homeUiState.songsList.collectAsState()
    val isShuffle by homeUiState.isShuffle.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
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
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
                IconButton(
                    onClick = { onSettingsIconClicked() }, modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = null,
                    )
                }
            }
            Divider(color = MaterialTheme.colorScheme.primary, thickness = 3.dp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                text = "Songs List",
                color = MaterialTheme.colorScheme.primary,
                fontFamily = NotoSerif,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .height(48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onPlayListButtonClicked() },
                    elevation = ButtonDefaults.buttonElevation(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_playlist),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(32.dp))
                Button(
                    onClick = { onShuffleIconToggled() },
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    colors = buttonColors(
                        containerColor = if (isShuffle) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Red,
                        disabledContentColor = Color.Red
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shuffle),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.primary,
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            LazyColumn(
            ) {
                playList.forEach { song ->
                    item {
                        SongRow(
                            song.name,
                            song.index,
                            isInPlaylist = song.isInPlaylist,
                            isForSettings = false
                        ) { onSongClicked(song.name, song.index) }
                    }
                }
            }
        }
    }
}
