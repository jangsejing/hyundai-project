package com.jess.hyundai.feature.detail.presentation.wikipedia.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jess.hyundai.feature.detail.R
import com.jess.hyundai.feature.detail.presentation.common.DetailBasicItem
import com.jess.hyundai.feature.detail.presentation.common.DetailDivider
import com.jess.hyundai.feature.detail.presentation.common.DetailTagsItem
import com.jess.hyundai.feature.detail.presentation.wikipedia.WikipediaDetailViewModel
import com.jess.hyundai.ui.component.JessAppBar
import com.jess.hyundai.ui.component.JessAsyncImage
import com.jess.hyundai.ui.webview.ComposeWebView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WikipediaDetailScreen(
    viewModel: WikipediaDetailViewModel,
    onBackPress: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            JessAppBar(
                title = {
                    Text(stringResource(id = R.string.wikipedia_detail_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPress,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.wikipedia_detail_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier.padding(innerPadding)
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            DetailTagsItem(
                tags = uiState.keywords,
                onTagClick = { tag ->

                }
            )

            DetailDivider()

            DetailBasicItem(
                title = stringResource(id = R.string.wikipedia_detail_page_title),
                content = uiState.title,
            )

            DetailDivider()

            DetailBasicItem(
                title = stringResource(id = R.string.wikipedia_detail_extract),
                content = uiState.extract,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )

            Spacer(modifier = Modifier.height(24.dp))

            JessAsyncImage(
                url = uiState.imageUrl,
                contentDescription = uiState.keywords.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(160.dp),
            )

            if (uiState.webUrl.isNullOrBlank().not()) {
                ComposeWebView(
                    url = uiState.webUrl.orEmpty(),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

