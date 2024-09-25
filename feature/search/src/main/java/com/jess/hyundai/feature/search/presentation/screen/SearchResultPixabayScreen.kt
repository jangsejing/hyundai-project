package com.jess.hyundai.feature.search.presentation.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jess.hyundai.feature.search.R
import com.jess.hyundai.model.entity.PixabayHitEntity
import com.jess.hyundai.ui.component.JessAsyncImage

@Composable
fun PixabaySearchResult(
    entity: PixabayHitEntity,
    onClick: (PixabayHitEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    var extended by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        PixabaySearchResultItem(
            imageUrl = entity.imageUrl.orEmpty(),
            tags = entity.tags,
            user = entity.user.orEmpty(),
            width = entity.width,
            height = entity.height,
            views = entity.views,
            downloads = entity.downloads,
            onClick = {
                onClick(entity)
            },
            getExtended = {
                extended
            },
        )

        PixabayExtendButton(
            modifier = Modifier.align(
                Alignment.BottomEnd,
            ),
            onClick = {
                extended = extended.not()
            },
            getExtended = {
                extended
            },
        )
    }
}

@Composable
private fun PixabaySearchResultItem(
    imageUrl: String,
    tags: List<String>,
    user: String,
    width: Int?,
    height: Int?,
    views: Long?,
    downloads: Long?,
    onClick: () -> Unit,
    getExtended: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    val extended = getExtended()

    val changedHeight by animateDpAsState(
        targetValue = if (extended) {
            120.dp
        } else {
            80.dp
        },
        label = "DpAnimation",
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(changedHeight),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
        ) {
            JessAsyncImage(
                url = imageUrl,
                contentDescription = tags.toString(),
                modifier = Modifier
                    .size(
                        width = 80.dp,
                        height = changedHeight,
                    )
                    .clip(RoundedCornerShape(size = 4.dp)),
            )

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier.padding(4.dp),
            ) {
                Text(
                    text = user,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )

                Spacer(modifier = Modifier.height(4.dp))

                val body = buildAnnotatedString {
                    if (tags.isNotEmpty()) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_tag,
                            ).format(tags.joinToString(", ")),
                        )
                    }

                    if (width != null && height != null) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_size,
                            ).format(width, height),
                        )
                    }

                    if (views != null) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_views,
                            ).format(views),
                        )
                    }

                    if (downloads != null) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_downloads,
                            ).format(downloads),
                        )
                    }
                }

                Text(
                    text = body,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (extended) {
                        Int.MAX_VALUE
                    } else {
                        2
                    },
                )
            }
        }
    }
}

@Composable
private fun PixabayExtendButton(
    onClick: () -> Unit,
    getExtended: () -> Boolean, // true 펼침, false 닫힘 (기본값)
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = if (getExtended()) {
                Icons.Filled.KeyboardArrowUp
            } else {
                Icons.Filled.KeyboardArrowDown
            },
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )
    }
}
