package com.jess.hyundai.feature.search.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jess.hyundai.domain.model.WikipediaPageEntity
import com.jess.hyundai.feature.search.R

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
            if (entity.imageUrl.isNullOrBlank().not()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(entity.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = entity.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(160.dp)
                        .clip(
                            RoundedCornerShape(4.dp)
                        )
                )
            } else {
                Box(
                    modifier = Modifier.height(160.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "\uD83D\uDEE0",
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                emojiSupportMatch = EmojiSupportMatch.None
                            )
                        )
                    )
                }
            }

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