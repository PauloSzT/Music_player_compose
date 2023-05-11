package com.android.example.music.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.example.music.MainActivityViewModel
import com.android.example.music.databinding.FragmentHomeBinding
import com.android.example.music.ui.theme.MusicAppTheme

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MusicAppTheme {
                    HomeView(
                        homeUiState = activityViewModel.homeUiState,
                        onSongClicked = { name, index ->
                            activityViewModel.musicPlayer?.playSong(index)
                            navigateToPlayFragment(name)
                        },
                        onPlayListButtonClicked = {
                            activityViewModel.musicPlayer?.let { player ->
                                val songIndex = player.playList()
                                navigateToPlayFragment(player.playList[songIndex].name)
                            }
                        },
                        onSettingsIconClicked = { navigateToSettingsFragment() },
                        onShuffleIconToggled = {
                            activityViewModel.musicPlayer?.toggleShuffle()
                        }
                    )
                }
            }
        }
        return binding.root
    }

    private fun navigateToPlayFragment(songName: String) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToPlayFragment(songName)
        findNavController().navigate(action)
    }

    private fun navigateToSettingsFragment() {
        val action =
            HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
        findNavController().navigate(action)
    }
}
