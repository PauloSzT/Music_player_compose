package com.android.example.music.ui.playfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.example.music.MainActivityViewModel
import com.android.example.music.databinding.FragmentPlayBinding
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
                    PlayView(
                        playUiState = activityViewModel.playUiState,
                        onSettingsIconClicked = {navigateToSettingsFragment()},
                    )
                }
            }
        }
        return binding.root
    }

    private fun navigateToSettingsFragment() {
        val action =
            PlayFragmentDirections.actionPlayFragmentToSettingsFragment()
        findNavController().navigate(action)
    }
}
