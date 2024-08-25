package com.jess.hyundai.feature.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jess.hyundai.feature.home.presentation.screen.HomeScreen
import com.jess.hyundai.navigator.Navigator
import com.jess.hyundai.ui.theme.HyundaiTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    companion object {

        fun newIntent(
            context: Context,
        ): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyundaiTheme {
                HomeScreen(
                    getIntent = { direction ->
                        navigator.getIntent(this, direction)
                    },
                    onBackPress = {
                        finish()
                    }
                )
            }
        }
    }

}