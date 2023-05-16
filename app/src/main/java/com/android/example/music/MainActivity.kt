package com.android.example.music

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Build
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
    }

@SuppressLint("UnspecifiedRegisterReceiverFlag")
private fun initializeBroadcastReceiver() {
        val filter = IntentFilter(BROADCAST_ACTION)
        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.TIRAMISU){
            this.registerReceiver(MusicBroadcastReceiver(), filter)
            return
        }
    this.registerReceiver(MusicBroadcastReceiver(), filter, RECEIVER_NOT_EXPORTED)
    }

    companion object {
        private const val BROADCAST_ACTION = "com.example.music.MUSIC_BROADCAST"
    }
}
