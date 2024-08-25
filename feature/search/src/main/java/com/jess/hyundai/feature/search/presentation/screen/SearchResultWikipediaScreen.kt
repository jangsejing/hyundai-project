package com.jess.hyundai.feature.search.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jess.hyundai.model.entity.WikipediaPageEntity
import com.jess.hyundai.feature.search.R
import com.jess.hyundai.ui.component.JessAsyncImage

/**
 * 위키 검색 결과
 */
@Composable
internal fun WikipediaSearchResult(
    entities: List<WikipediaPageEntity>,
    onClick: (WikipediaPageEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.search_wikipedia_result).format(entities.size),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            entities.forEachIndexed { index, entity ->
                WikipediaSearchResultItem(
                    entity = entity,
                    onClick = onClick,
                )
                // 여백 추가
                if (index != entities.lastIndex) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

/**
 * 위키 검색결과 Item
 */
@Composable
internal fun WikipediaSearchResultItem(
    entity: WikipediaPageEntity,
    onClick: (WikipediaPageEntity) -> Unit,
    modifier: Modifier = Modifier,
) {

    val configuration = LocalConfiguration.current
    // 스크린 width - 좌,우 여백 - 중간 여백
    val screenWidth = configuration.screenWidthDp.dp - (24.dp * 2) - 16.dp
    val itemWidth = screenWidth / 3

    Card(
        modifier = modifier
            .width(itemWidth),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp),
                )
                .clickable(
                    onClick = {
                        onClick(entity)
                    }
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JessAsyncImage(
                modifier = Modifier
                    .height(160.dp)
                    .clip(RoundedCornerShape(size = 4.dp)),
                url = entity.imageUrl,
                contentDescription = entity.title
            )

            if (entity.title.isNullOrBlank().not()) {
                Text(
                    modifier = Modifier.padding(
                        vertical = 4.dp,
                        horizontal = 2.dp,
                    ),
                    text = entity.title.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}