package com.android.example.music.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.music.R
import com.android.example.music.ui.theme.MusicAppTheme

@Composable
fun SongRow(songName:String, songIndex:Int, onSongClicked: (Int) -> Unit ) {
    Row(modifier = Modifier
        .clickable { onSongClicked(songIndex) }
        .fillMaxWidth()
        .padding( vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_music),
            contentDescription = null
        )
        Text(
            text = songName,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun SongRowPreview(){
    MusicAppTheme {
        SongRow("cancion",0) {}
    }
}