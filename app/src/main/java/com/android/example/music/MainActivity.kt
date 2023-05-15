package com.android.example.music

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.example.music.broadcast.MusicBroadcastReceiver
import com.android.example.music.models.SongProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initializeBroadcastReceiver()
        SongProvider(contentResolver)
        viewModelFactory = MainActivityViewModelFactory(application)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        viewModel.initializePlayer()
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun initializeBroadcastReceiver() {
        val filter = IntentFilter(BROADCAST_ACTION)
        this.registerReceiver(MusicBroadcastReceiver(), filter)
    }

    companion object {
        private const val BROADCAST_ACTION = "com.example.music.MUSIC_BROADCAST"
    }
}
