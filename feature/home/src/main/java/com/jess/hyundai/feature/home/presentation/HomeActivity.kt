package com.jess.hyundai.feature.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jess.hyundai.feature.home.presentation.screen.HomeScreen
import com.jess.hyundai.ui.theme.HyundaiTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyundaiTheme {
                HomeScreen(
                    onBackPress = {
                        finish()
                    }
                )
            }
        }
    }

}