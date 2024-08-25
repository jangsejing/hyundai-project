package com.jess.hyundai.feature.detail.presentation.pixabay.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jess.hyundai.feature.detail.R
import com.jess.hyundai.feature.detail.presentation.common.DetailBasicItem
import com.jess.hyundai.feature.detail.presentation.common.DetailDivider
import com.jess.hyundai.feature.detail.presentation.common.DetailTagsItem
import com.jess.hyundai.feature.detail.presentation.pixabay.PixabayDetailViewModel
import com.jess.hyundai.ui.component.JessAppBar
import com.jess.hyundai.ui.component.JessAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixabayDetailScreen(
    viewModel: PixabayDetailViewModel,
    onTagClick: (String) -> Unit,
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
                    Text(stringResource(id = R.string.pixabay_detail_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPress,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.pixabay_detail_back)
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

            DetailBasicItem(
                title = stringResource(id = R.string.pixabay_detail_user),
                content = uiState.user,
            )

            DetailDivider()

            DetailTagsItem(
                tags = uiState.tags,
                onTagClick = onTagClick,
            )

            DetailDivider()

            DetailBasicItem(
                title = stringResource(id = R.string.pixabay_detail_size),
                content = stringResource(
                    id = R.string.pixabay_detail_size_content
                ).format(
                    uiState.width,
                    uiState.height
                ),
            )

            DetailDivider()

            DetailBasicItem(
                title = stringResource(id = R.string.pixabay_detail_views),
                content = stringResource(
                    id = R.string.pixabay_detail_views_content
                ).format(uiState.views),
            )

            DetailDivider()

            DetailBasicItem(
                title = stringResource(id = R.string.pixabay_detail_downloads),
                content = stringResource(
                    id = R.string.pixabay_detail_views_content
                ).format(uiState.downloads),
            )

            Spacer(modifier = Modifier.height(24.dp))

            JessAsyncImage(
                url = uiState.imageUrl,
                contentDescription = uiState.tags.toString(),
                modifier = Modifier
                    .background(Color.White)
                    .weight(1f),
            )
        }
    }
}
