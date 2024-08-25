package com.jess.hyundai.feature.detail.presentation.pixabay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jess.hyundai.feature.detail.presentation.pixabay.screen.PixabayDetailScreen
import com.jess.hyundai.ui.theme.HyundaiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PixabayDetailActivity : ComponentActivity() {

    private val viewModel: PixabayDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyundaiTheme {
                PixabayDetailScreen(
                    viewModel = viewModel,
                    onBackPress = {
                        finish()
                    }
                )
            }
        }
    }
}