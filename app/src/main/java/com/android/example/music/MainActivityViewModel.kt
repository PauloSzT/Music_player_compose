package com.android.example.music

import android.app.Application
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.music.broadcast.MusicBroadcastReceiver
import com.android.example.music.models.Song
import com.android.example.music.models.SongProvider
import com.android.example.music.ui.homefragment.HomeUiState
import com.android.example.music.ui.playfragment.PlayUiState
import com.android.example.music.ui.settingsfragment.SettingsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel(private val application: Application) : ViewModel() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private var isSeekBarRunning = false
    private var currentSongIndex: Int = 0

    private val songProvider: SongProvider = SongProvider(application.contentResolver)
    private val songsList = MutableStateFlow<List<Song>>(emptyList())
    private val playlist: StateFlow<List<Song>> =
        songsList.map { list ->
            list.filter { song -> song.isInPlaylist }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily, emptyList()
        )

    private val seekbarPosition = MutableStateFlow(Pair(0f, 0f))
    private val currentSong = MutableStateFlow<Song?>(null)
    private val isPlaying = MutableStateFlow(false)
    private val isShuffle = MutableStateFlow(false)
    private val _isLoading = MutableStateFlow(false)
    private val isLoading = _isLoading.asStateFlow()


    val homeUiState = HomeUiState(
        songsList = playlist,
        isShuffle = isShuffle,
        isLoading = isLoading,
        onShuffleIconToggled = ::toggleShuffle,
        refresh = ::refresh
    )
    val playUiState = PlayUiState(currentSong, isPlaying, seekbarPosition, ::onSeekbarChange)
    val settingsUiState = SettingsUiState(songsList, isLoading, ::refresh)

    private fun toggleShuffle() {
        isShuffle.value = !isShuffle.value
    }

    fun addSongOrRemove(songIndex: Int) {
        songsList.update { songs ->
            songs.mapIndexed { index, song ->
                if (index == songIndex) {
                    Song(
                        song.path,
                        song.name,
                        !song.isInPlaylist
                    )
                }else{
                    song
                }
            }
        }
    }

    /** Play the playlist on HomeView */
    fun playList(): Int {
        return if (isShuffle.value) {
            val random = (playlist.value.indices).random()
            playSong(random)
            mediaPlayer.setOnCompletionListener {
                val randomNext = (playlist.value.indices).random()
                playSong(randomNext)
            }
            random
        } else {
            playSong(0)
            mediaPlayer.setOnCompletionListener { playSong(currentSongIndex + 1) }
            0
        }
    }

    fun skipNext() {
        if (isShuffle.value) {
            val random = (playlist.value.indices).random()
            playSong(random)
            currentSong.value = playlist.value[random]
        } else {
            if (currentSongIndex == playlist.value.size - 1) {
                playSong(0)
                currentSong.value = playlist.value[0]
            } else {
                playSong(currentSongIndex + 1)
                currentSong.value = playlist.value[currentSongIndex]
            }
        }
    }

    fun skipPrev() {
        if (currentSongIndex == 0) {
            playSong(playlist.value.size - 1)
            currentSong.value = playlist.value[playlist.value.size - 1]
        } else {
            playSong(currentSongIndex - 1)
            currentSong.value = playlist.value[currentSongIndex]
        }
    }

    fun pauseOrResumeCurrentSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlaying.value = false
        } else {
            mediaPlayer.start()
            isPlaying.value = true
        }
    }

    /** Main Fun to Play a Song */
    fun playSong(songIndex: Int) {
        destroyPlayer()
        mediaPlayer.setDataSource(application, Uri.parse(playlist.value[songIndex].path))
        mediaPlayer.prepare()
        mediaPlayer.setVolume(1.0f, 1.0f)
        mediaPlayer.start()
        currentSongIndex = songIndex
        sendBroadcast(playlist.value[currentSongIndex].name)
        currentSong.value = playlist.value[currentSongIndex]
        initializeSeekBar()
        isPlaying.value = true
    }

    /** Main fun to collect the List from Provider. */
    fun initializePlayer() {
        val list = songProvider.getSongsList().mapIndexed { index, item ->
            val metadataRetriever = MediaMetadataRetriever()
            val uri = Uri.parse(item)
            metadataRetriever.setDataSource(application, uri)
            val trackName =
                metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                    .toString()
            Song(
                path = item,
                name = trackName,
                isInPlaylist = (0..2).contains(index)
            )
        }
        songsList.value = list
    }

    private fun initializeSeekBar() {
        viewModelScope.launch {
            isSeekBarRunning = true
            while (isSeekBarRunning) {
                seekbarPosition.value =
                    Pair(mediaPlayer.currentPosition.toFloat(), mediaPlayer.duration.toFloat())
                delay(1000)
            }
        }
    }

    private fun sendBroadcast(songName: String) {
        val intent = Intent(application, MusicBroadcastReceiver::class.java)
        intent.action = BROADCAST_ACTION
        intent.putExtra("songName", songName)
        application.sendBroadcast(intent)
    }

    private fun refresh() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)
            initializePlayer()
            _isLoading.value = false
        }
    }

    private fun onSeekbarChange(position: Float) {
        mediaPlayer.seekTo(position.toInt())
    }

    private fun destroyPlayer() {
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    companion object {
        const val BROADCAST_ACTION = "com.example.music.MUSIC_BROADCAST"
    }
}
