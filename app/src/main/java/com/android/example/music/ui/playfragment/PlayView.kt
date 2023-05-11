package com.android.example.music.ui.playfragment

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.example.music.R
import com.android.example.music.ui.components.MusicSeekbar


@Composable
fun PlayView(
    playUiState: PlayUiState,
    onSettingsIconClicked: () -> Unit,
    onPlayButtonClicked: () -> Unit,
    onBackwardButtonClicked: () -> Unit,
    onForwardButtonClicked: () -> Unit,
) {
    val song by playUiState.song.collectAsState()
    val isPlaying by playUiState.isPlaying.collectAsState()

    val actionIcon by remember(isPlaying) {
        mutableStateOf(if (isPlaying) R.drawable.ic_play_arrow else R.drawable.ic_pause_circle)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Music Media Player",
                    color = Color.White,
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
            Divider(modifier = Modifier.size(3.dp), color = Color.LightGray)
            Image(
                modifier = Modifier.height(350.dp),
                painter = painterResource(R.drawable.music),
                contentDescription = null
            )
            Text(
                text = song?.name ?: "",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
            MusicSeekbar(playUiState)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onPlayButtonClicked() },
                elevation = ButtonDefaults.buttonElevation(10.dp),
            ) {
                Icon(
                    painter = painterResource(
                        id = actionIcon
                    ),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onBackwardButtonClicked() },
                    elevation = ButtonDefaults.buttonElevation(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_backward),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(60.dp))
                Button(
                    onClick = { onForwardButtonClicked() },
                    elevation = ButtonDefaults.buttonElevation(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_forward),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
