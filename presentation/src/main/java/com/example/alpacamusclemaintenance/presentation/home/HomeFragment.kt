package com.example.alpacamusclemaintenance.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alpacamusclemaintenance.domain.home.Home
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                HomeView()
            }
        }
    }

    @Composable
    private fun HomeView(viewModel: HomeViewModel = viewModel()) {
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
}
