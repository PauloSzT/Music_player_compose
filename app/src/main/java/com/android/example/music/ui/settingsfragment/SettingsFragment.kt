package com.android.example.music.ui.settingsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.example.music.MainActivityViewModel
import com.android.example.music.databinding.FragmentSettingsBinding
import com.android.example.music.ui.theme.MusicAppTheme

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MusicAppTheme {
                    SettingsView(
                        settingsUiState = activityViewModel.settingsUiState,
                        onSongClicked = {index ->
                            activityViewModel.musicPlayer?.addSongOrRemove(index)}
                    )
                }
            }
            return binding.root
        }
    }
}
