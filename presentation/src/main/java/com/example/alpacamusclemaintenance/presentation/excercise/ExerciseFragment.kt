package com.example.alpacamusclemaintenance.presentation.excercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alpacamusclemaintenance.presentation.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class ExerciseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                Box {
                    val pagerState = rememberPagerState()

                    HorizontalPager(
                        count = 2,
                        state = pagerState
                    ) { page ->
                        when (page) {
                            0 -> PushUp()
                            1 -> Squat()
                        }
                    }

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PushUp(viewModel: PushUpViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var pushUpCount: Int by remember { mutableStateOf(0) }

        Text(
            text = "Push up",
            modifier = Modifier.align(Alignment.TopCenter),
            style = MaterialTheme.typography.h5
        )

        Button(
            onClick = { pushUpCount++ },
            modifier = Modifier
                .align(Alignment.Center)
                .size(300.dp),
            shape = CircleShape
        ) {
            Text(
                text = pushUpCount.takeIf { it > 0 }?.toString()
                    ?: stringResource(R.string.push_up_initial_text)
            )
        }

        FloatingActionButton(
            onClick = {
                viewModel.save(pushUpCount)
                pushUpCount = 0
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Squat() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Squat",
            modifier = Modifier.align(Alignment.TopCenter),
            style = MaterialTheme.typography.h5
        )

        Text(
            text = "Coming soon...",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
