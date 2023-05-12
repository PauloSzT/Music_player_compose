package com.android.example.music.player

import android.icu.text.Transliterator
import android.media.MediaPlayer
import androidx.compose.animation.core.updateTransition
import androidx.lifecycle.MutableLiveData
import com.android.example.music.models.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration


interface MusicPlayer {

    fun playSong(songIndex: Int)
    fun pauseOrResumeCurrentSong(): Boolean
    fun skipPrev()
    fun skipNext()
    fun toggleShuffle()
    fun playList(): Int
    fun seekTo(position: Int)
    fun addSongOrRemove(songIndex: Int)
    fun destroyPlayer()
}

class MusicPlayerImplementation(
    private var songsList: List<Song>,
    var playList: List<Song>,
    private val viewModelScope: CoroutineScope,
    val sendBroadcastCallback: (String) -> Unit,
    val updatePlayList: (List<Song>) -> Unit,
    val updateSeekbar: (Float, Float) -> Unit,
    val updateCurrentSong: (Song) -> Unit,
    val updateSongList: (List<Song>) -> Unit
) : MusicPlayer {

    var mediaPlayer: MediaPlayer = MediaPlayer()
    private var currentSongIndex: Int = 0
    val isShuffle = MutableLiveData(false)
    private var isSeekBarRunning = false


    override fun playSong(songIndex: Int) {
        destroyPlayer()
        mediaPlayer.setDataSource(playList[songIndex].path)
        mediaPlayer.prepare()
        mediaPlayer.setVolume(1.0f, 1.0f)
        mediaPlayer.start()
        currentSongIndex = songIndex
        sendBroadcastCallback(playList[currentSongIndex].name)
        updateCurrentSong(playList[currentSongIndex])
        initializeSeekBar()
    }

    override fun pauseOrResumeCurrentSong(): Boolean {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            return true
        } else {
            mediaPlayer.start()
            return false
        }
    }

    override fun skipPrev(){
        if (currentSongIndex == 0) {
            playSong(playList.size - 1)
            updateCurrentSong(playList[playList.size - 1])
        } else {
            playSong(currentSongIndex - 1)
            updateCurrentSong(playList[currentSongIndex])
        }
    }

    override fun skipNext(){
        if (isShuffle.value == true) {
            val random = (playList.indices).random()
            playSong(random)
            updateCurrentSong(playList[random])
        } else {
            if (currentSongIndex == playList.size - 1) {
                playSong(0)
                 updateCurrentSong(playList[0])
            } else {
                playSong(currentSongIndex + 1)
                updateCurrentSong(playList[currentSongIndex])
            }
        }
    }

    override fun playList() : Int {
        if (isShuffle.value == true) {
            val random = (playList.indices).random()
            playSong(random)
            mediaPlayer.setOnCompletionListener {
                val randomNext = (playList.indices).random()
                playSong(randomNext)
            }
            return random
        } else {
            playSong(0)
            mediaPlayer.setOnCompletionListener { playSong(currentSongIndex + 1) }
            return 0
        }
    }

    override fun seekTo(position: Int) {
        mediaPlayer.seekTo(position)
    }

    override fun toggleShuffle() {
        isShuffle.value?.let {
            isShuffle.value = !it
        }
    }

    override fun addSongOrRemove(songIndex: Int) {
        songsList[songIndex].isInPlaylist.value = !songsList[songIndex].isInPlaylist.value
        playList = songsList.filter{
            it.isInPlaylist.value
        }.mapIndexed { index, song ->
            song.index = index
            song
        }
        updatePlayList(playList)
        updateSongList(songsList)
    }

    override fun destroyPlayer() {
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    private fun initializeSeekBar() {
        viewModelScope.launch {
            isSeekBarRunning = true
            while (isSeekBarRunning) {
                updateSeekbar(mediaPlayer.currentPosition.toFloat(), mediaPlayer.duration.toFloat())
                delay(1000)
            }
        }
    }
}
