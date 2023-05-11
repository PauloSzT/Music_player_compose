package com.android.example.music

import android.app.Application
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.music.broadcast.MusicBroadcastReceiver
import com.android.example.music.models.Song
import com.android.example.music.models.SongProvider
import com.android.example.music.player.MusicPlayerImplementation
import com.android.example.music.ui.homefragment.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel(private val application: Application) : ViewModel() {

    var musicPlayer: MusicPlayerImplementation? = null
    private val songProvider: SongProvider = SongProvider(application.contentResolver)
    private val _songsList = MutableStateFlow<List<Song>>(emptyList())
    val songsList = _songsList.asStateFlow()
    private val _playList = MutableStateFlow<List<Song>>(emptyList())
    val playList = _playList.asStateFlow()
    val homeUiState = HomeUiState(playList)

    fun initializePlayer() {
        val list = songProvider.getSongsList().mapIndexed { index, item ->
            val metadataRetriever = MediaMetadataRetriever()
            val uri = Uri.parse("$CHILD_ROUTE$item")
            metadataRetriever.setDataSource(application, uri)
            val trackName =
                metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                    .toString()
            Song(
                path = "$CHILD_ROUTE$item",
                index = index,
                name = trackName,
                isInPlaylist = (0..2).contains(index)
            )
        }
        _songsList.value = list
        _playList.value = list.filter {
            it.isInPlaylist
        }
        musicPlayer =
            MusicPlayerImplementation(
                _songsList.value,
                _playList.value,
                viewModelScope,
                ::sendBroadcast,
                ::onUpdatePlayList
            )
    }

    private fun sendBroadcast(songName: String) {
        val intent = Intent(application, MusicBroadcastReceiver::class.java)
        intent.action = BROADCAST_ACTION
        intent.putExtra("songName", songName)
        application.sendBroadcast(intent)
    }

    private fun onUpdatePlayList(list: List<Song>){
        _playList.value = list
    }

    companion object {
        const val CHILD_ROUTE = "/storage/emulated/0/"
        const val BROADCAST_ACTION = "com.example.music.MUSIC_BROADCAST"
    }
}
