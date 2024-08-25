package com.jess.hyundai.feature.detail.presentation.pixabay

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jess.hyundai.domain.model.PixabayHitEntity
import com.jess.hyundai.feature.detail.presentation.pixabay.screen.PixabayDetailScreen
import com.jess.hyundai.ui.theme.HyundaiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PixabayDetailActivity : ComponentActivity() {

    companion object {

        const val EXTRA_PIXABAY_ENTITY = "extra_pixabay_entity"

        fun newIntent(
            context: Context,
            entity: PixabayHitEntity,
        ): Intent {
            return Intent(context, PixabayDetailActivity::class.java).apply {
                putExtra(EXTRA_PIXABAY_ENTITY, entity)
            }
        }
    }

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