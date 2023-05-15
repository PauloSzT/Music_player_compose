package com.android.example.music.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Refresher(
    isLoadingState: StateFlow<Boolean>,
    refresh: () -> Unit,
    content: @Composable () -> Unit
) {
    val isLoading by isLoadingState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isLoading, {refresh()})
    Box(modifier= Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)
    ) {
        content()
        PullRefreshIndicator(isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
