package com.jess.hyundai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

@Composable
fun JessCircularProgress() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.4f)
            .background(
                Color.Black,
            ),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
