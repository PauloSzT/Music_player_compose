package com.android.example.music.ui.playfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.example.music.MainActivityViewModel
import com.android.example.music.R
import com.android.example.music.databinding.FragmentPlayBinding
import com.android.example.music.ui.homefragment.HomeView
import com.android.example.music.ui.theme.MusicAppTheme

class PlayFragment : Fragment() {
    private lateinit var binding: FragmentPlayBinding
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MusicAppTheme {
                    PlayView(playUiState = activityViewModel.playUiState)
                }
            }
        }
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val bundle = arguments
//        val args = bundle?.let { PlayFragmentArgs.fromBundle(it) }
//        binding.songName.text = args?.songname
//        binding.fabSettings.setOnClickListener {
//            val action = PlayFragmentDirections.actionPlayFragmentToSettingsFragment()
//            findNavController().navigate(action)
//        }
//
//        val seekBarObserver = Observer<Pair<Int, Int>> { pair ->
//            binding.seekbar.progress = pair.first
//            binding.seekbar.max = pair.second
//        }
//        activityViewModel.musicPlayer.value?.seekBarPosition?.observe(
//            requireActivity(),
//            seekBarObserver
//        )
//        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(
//                seekBar: SeekBar?,
//                progress: Int,
//                fromUser: Boolean
//            ) {
//                if (fromUser) activityViewModel.musicPlayer.value?.seekTo(progress)
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
//            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
//        })
//
//        binding.fabPlay.setOnClickListener {
//            if (activityViewModel.musicPlayer.value?.pauseOrResumeCurrentSong() == true) {
//                binding.fabPlay.setImageResource(R.drawable.ic_play_arrow)
//            } else {
//                binding.fabPlay.setImageResource(R.drawable.ic_pause_circle)
//            }
//        }
//
//        binding.fabBackward.setOnClickListener {
//            activityViewModel.musicPlayer.value?.let { player ->
//                binding.songName.text = player.skipPrev()
//            }
//
//        }
//        binding.fabForward.setOnClickListener {
//            activityViewModel.musicPlayer.value?.let { player ->
//                binding.songName.text = player.skipNext()
//            }
//        }
//    }
}

