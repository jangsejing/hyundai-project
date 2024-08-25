package com.jess.hyundai.feature.detail.presentation.wikipedia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jess.hyundai.feature.detail.presentation.wikipedia.screen.WikipediaDetailScreen
import com.jess.hyundai.model.constant.EXTRA_TAG
import com.jess.hyundai.model.entity.WikipediaPageEntity
import com.jess.hyundai.ui.theme.HyundaiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WikipediaDetailActivity : ComponentActivity() {

    companion object {

        const val EXTRA_WIKIPEDIA_ENTITY = "extra_wikipedia_entity"

        fun newIntent(
            context: Context,
            entity: WikipediaPageEntity,
        ): Intent {
            return Intent(context, WikipediaDetailActivity::class.java).apply {
                putExtra(EXTRA_WIKIPEDIA_ENTITY, entity)
            }
        }
    }

    private val viewModel: WikipediaDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyundaiTheme {
                WikipediaDetailScreen(
                    viewModel = viewModel,
                    onTagClick = { tag ->
                        setResult(
                            RESULT_OK,
                            Intent().apply {
                                putExtra(EXTRA_TAG, tag)
                            }
                        )
                        finish()
                    },
                    onBackPressed = {
                        finish()
                    }
                )
            }
        }
    }
}