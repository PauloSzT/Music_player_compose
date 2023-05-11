package com.android.example.music.ui.homefragment

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.music.MainActivityViewModel
import com.android.example.music.R
import com.android.example.music.models.Song
import com.android.example.music.ui.components.SongRow
import com.android.example.music.ui.theme.MusicAppTheme
import com.android.example.music.ui.theme.NotoSerif
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeView(
    homeUiState: HomeUiState,
    onSongClicked: (String, Int) -> Unit,
    onPlayListButtonClicked: () -> Unit,
    onSettingsIconClicked: () -> Unit,
    onShuffleIconToggled: () -> Unit
) {
    val songsList by homeUiState.playList.collectAsState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
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
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                IconButton(
                    onClick = { onSettingsIconClicked() },
                    modifier = Modifier
                        .padding(end = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = null,
                    )
                }
            }
            Divider(color = Color.LightGray, thickness = 3.dp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                text = "Songs List",
                color = Color.White,
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
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(32.dp))
                Button(
                    onClick = { onShuffleIconToggled() },
                    elevation = ButtonDefaults.buttonElevation(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shuffle),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            LazyColumn(
            ) {
                songsList.forEach { song ->
                    item {
                        SongRow(song.name, song.index) { onSongClicked(song.name, song.index) }
                    }
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeViewPreview() {
    val homeUiState = HomeUiState(
        MutableStateFlow<List<Song>>(
            listOf(
                Song("", 0, "cancion1", true),
                Song("", 0, "cancion2", true),
                Song("", 0, "cancion3", true)
            )
        )
    )
    MusicAppTheme {
        HomeView(
            homeUiState = homeUiState,
            onSongClicked = { _, _ -> },
            onPlayListButtonClicked = {},
            onSettingsIconClicked = {}) {

        }
    }
}
