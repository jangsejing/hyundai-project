package com.jess.hyundai.feature.search.presentation.screen

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jess.hyundai.feature.search.R
import com.jess.hyundai.feature.search.presentation.SearchResultContentUiState
import com.jess.hyundai.feature.search.presentation.SearchResultItem
import com.jess.hyundai.feature.search.presentation.SearchResultUiState
import com.jess.hyundai.feature.search.presentation.SearchResultViewModel
import com.jess.hyundai.model.constant.EXTRA_TAG
import com.jess.hyundai.model.entity.PixabayHitEntity
import com.jess.hyundai.model.entity.WikipediaPageEntity
import com.jess.hyundai.navigator.Direction
import com.jess.hyundai.ui.component.JessAppBar
import com.jess.hyundai.ui.component.JessCircularProgress
import com.jess.hyundai.ui.component.JessInfinityLazyColumn
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchResultScreen(
    viewModel: SearchResultViewModel,
    getIntent: (Direction) -> Intent,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            viewModel.onSearch(
                query = result.data?.getStringExtra(EXTRA_TAG),
            )
        }
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
                        onClick = onBackPressed,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.search_back),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        // content
        if (uiState.query.isNullOrBlank().not()) {
            SearchResultContent(
                uiState = uiState,
                modifier = Modifier.padding(innerPadding),
                onPixabayClick = { entity ->
                    launcher.launch(
                        getIntent(Direction.PixabayDetail(entity)),
                    )
                },
                onWikipediaClick = { entity ->
                    launcher.launch(
                        getIntent(Direction.WikipediaDetail(entity)),
                    )
                },
                onLoadMore = {
                    viewModel.onLoadNextPage()
                },
                onBackPressed = onBackPressed,
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
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    // query 가 바뀌면 검색을 새로 했다고 판단, 스크롤 최상단으로 이동
    LaunchedEffect(uiState.query) {
        scope.launch {
            lazyListState.scrollToItem(0)
        }
    }

    Column(
        modifier = modifier.padding(
            horizontal = 24.dp,
        ),
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                    ),
                ) {
                    append(
                        stringResource(
                            id = R.string.search_result_query,
                        ).format(uiState.query),
                    )
                }
                append(
                    stringResource(id = R.string.search_result_tail),
                )
            },
            modifier = Modifier.padding(
                vertical = 12.dp,
            ),
            style = MaterialTheme.typography.titleLarge,
        )

        SearchResultList(
            state = uiState.state,
            items = uiState.items,
            onPixabayClick = onPixabayClick,
            onWikipediaClick = onWikipediaClick,
            onLoadMore = onLoadMore,
            onBackPressed = onBackPressed,
        )
    }
}

@Composable
private fun SearchResultList(
    state: SearchResultContentUiState,
    items: List<SearchResultItem>,
    onPixabayClick: (PixabayHitEntity) -> Unit,
    onWikipediaClick: (WikipediaPageEntity) -> Unit,
    onLoadMore: () -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val lazyListState = rememberLazyListState()

    when (state) {
        is SearchResultContentUiState.Succeeded -> {
            JessInfinityLazyColumn(
                modifier = modifier,
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                onLoadMore = onLoadMore,
            ) {
                itemsIndexed(
                    items = items,
                    key = { _, item ->
                        item.hashCode()
                    },
                    contentType = { _, item ->
                        item
                    },
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

        is SearchResultContentUiState.Empty -> {
            SearchResultEmptyPage(
                onBackPressed = onBackPressed,
            )
        }

        is SearchResultContentUiState.Failed -> {
            SearchResultFailedPage(
                message = state.message,
                onBackPressed = onBackPressed,
            )
        }

        else -> Unit // nothing
    }
}

/**
 * 검색을 했으나 데이터가 없을 경우 표시합니다.
 */
@Composable
private fun SearchResultEmptyPage(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "🫙",
            textAlign = TextAlign.Center,
            fontSize = 100.sp,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    emojiSupportMatch = EmojiSupportMatch.None,
                ),
            ),
        )

        Spacer(modifier = Modifier.height(56.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onBackPressed,
        ) {
            Text(text = stringResource(id = R.string.search_result_research))
        }
    }
}

/**
 * 검색을 하는 과정에서 Exception 이 발생했을 경우 표시합니다.
 */
@Composable
private fun SearchResultFailedPage(
    message: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "😇",
            textAlign = TextAlign.Center,
            fontSize = 100.sp,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    emojiSupportMatch = EmojiSupportMatch.None,
                ),
            ),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onBackPressed,
        ) {
            Text(text = stringResource(id = R.string.search_result_research))
        }
    }
}
