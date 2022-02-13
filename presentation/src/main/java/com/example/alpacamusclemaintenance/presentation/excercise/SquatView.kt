package com.example.alpacamusclemaintenance.presentation.excercise

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SquatView() {
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
