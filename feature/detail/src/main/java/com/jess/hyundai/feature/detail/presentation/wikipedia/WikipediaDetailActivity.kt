package com.jess.hyundai.feature.detail.presentation.wikipedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jess.hyundai.feature.detail.presentation.wikipedia.screen.WikipediaDetailScreen
import com.jess.hyundai.ui.theme.HyundaiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WikipediaDetailActivity : ComponentActivity() {

    private val viewModel: WikipediaDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyundaiTheme {
                WikipediaDetailScreen(
                    viewModel = viewModel,
                    onBackPress = {
                        finish()
                    }
                )
            }
        }
    }
}