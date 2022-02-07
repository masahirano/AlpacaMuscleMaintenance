package com.example.alpacamusclemaintenance.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alpacamusclemaintenance.domain.home.Home

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val defaultState = Home(wordOfWisdom = "", author = "")
    val uiState: Home by viewModel.data.observeAsState(defaultState)

    AnimatedVisibility(
        visible = true,
        enter = fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(durationMillis = 2_000)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = uiState.wordOfWisdom,
                style = MaterialTheme.typography.h5
            )

            Spacer(Modifier.height(64.dp))
            Text(
                text = uiState.author,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
