package com.example.alpacamusclemaintenance.presentation.excercise

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alpacamusclemaintenance.presentation.R

@Composable
internal fun PushUpView(viewModel: PushUpViewModel = hiltViewModel()) {
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
