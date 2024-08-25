package com.jess.hyundai.feature.search.presentation.screen

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jess.hyundai.domain.model.PixabayHitEntity
import com.jess.hyundai.domain.model.WikipediaPageEntity
import com.jess.hyundai.feature.search.R
import com.jess.hyundai.feature.search.presentation.SearchResultItem
import com.jess.hyundai.feature.search.presentation.SearchResultUiState
import com.jess.hyundai.feature.search.presentation.SearchResultViewModel
import com.jess.hyundai.navigator.Direction
import com.jess.hyundai.ui.component.JessAppBar
import com.jess.hyundai.ui.component.JessCircularProgress
import com.jess.hyundai.ui.component.JessInfinityLazyColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchResultScreen(
    viewModel: SearchResultViewModel,
    getIntent: (Direction) -> Intent,
    onBackPress: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {

    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            JessAppBar(
                title = {
                    Text(stringResource(id = R.string.search_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPress,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.search_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        // content
        if (uiState.query.isNullOrBlank().not()) {
            SearchResultContent(
                uiState = uiState,
                modifier = Modifier.padding(innerPadding),
                onPixabayClick = { entity ->
                    launcher.launch(
                        getIntent(Direction.PixabayDetail(entity))
                    )
                },
                onWikipediaClick = { entity ->
                    launcher.launch(
                        getIntent(Direction.WikipediaDetail(entity))
                    )
                },
                onLoadMore = {
                    viewModel.onLoadNextPage()
                }
            )
        }
    }

    // loading
    if (uiState.loading) {
        JessCircularProgress()
    }
}

/**
 * 검색 결과
 */
@Composable
private fun SearchResultContent(
    uiState: SearchResultUiState,
    onPixabayClick: (PixabayHitEntity) -> Unit,
    onWikipediaClick: (WikipediaPageEntity) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(
            horizontal = 24.dp
        )
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(
                        stringResource(
                            id = R.string.search_result_query
                        ).format(uiState.query)
                    )
                }
                append(
                    stringResource(id = R.string.search_result_tail)
                )
            },
            modifier = Modifier.padding(
                vertical = 12.dp,
            ),
            style = MaterialTheme.typography.titleLarge,
        )

        JessInfinityLazyColumn(
            onLoadMore = onLoadMore,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(
                items = uiState.items,
                key = { _, item ->
                    item.hashCode()
                },
                contentType = { _, item ->
                    item
                }
            ) { _, item ->
                // 위키 검색 결과
                when (item) {
                    is SearchResultItem.PixabayItem -> PixabaySearchResult(
                        entity = item.entity,
                        onClick = onPixabayClick,
                    )

                    is SearchResultItem.WikipediaItem -> WikipediaSearchResult(
                        entities = item.entities,
                        onClick = onWikipediaClick,
                    )
                }
            }
        }
    }
}
