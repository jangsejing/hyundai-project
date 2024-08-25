package com.jess.hyundai.feature.search.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jess.hyundai.feature.search.presentation.screen.SearchResultScreen
import com.jess.hyundai.navigator.Navigator
import com.jess.hyundai.ui.theme.HyundaiTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultActivity : ComponentActivity() {

    companion object {

        const val EXTRA_SEARCH_QUERY = "extra_query"

        fun newIntent(
            context: Context,
            query: String,
        ): Intent {
            return Intent(context, SearchResultActivity::class.java).apply {
                putExtra(EXTRA_SEARCH_QUERY, query)
            }
        }
    }

    @Inject
    lateinit var navigator: Navigator

    private val viewModel: SearchResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyundaiTheme {
                SearchResultScreen(
                    viewModel = viewModel,
                    getIntent = { direction ->
                        navigator.getIntent(this, direction)
                    },
                    onBackPressed = {
                        finish()
                    },
                )
            }
        }
    }
}
