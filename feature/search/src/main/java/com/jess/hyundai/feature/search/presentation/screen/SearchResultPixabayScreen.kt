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
import com.jess.hyundai.domain.model.PixabayHitEntity
import com.jess.hyundai.feature.search.R
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
            entity = entity,
            extended = extended,
            onClick = {
                onClick(entity)
            },
        )

        PixabayExtendButton(
            extended = extended,
            modifier = Modifier.align(
                Alignment.BottomEnd
            ),
            onClick = {
                extended = extended.not()
            }
        )
    }
}

@Composable
private fun PixabaySearchResultItem(
    entity: PixabayHitEntity,
    extended: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val height by animateDpAsState(
        targetValue = if (extended) {
            120.dp
        } else {
            80.dp
        },
        label = "DpAnimation"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
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
                url = entity.imageUrl,
                contentDescription = entity.tags.toString(),
                modifier = Modifier
                    .size(
                        width = 80.dp,
                        height = height,
                    )
                    .clip(RoundedCornerShape(size = 4.dp))
            )

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = entity.user.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )

                Spacer(modifier = Modifier.height(4.dp))

                val body = buildAnnotatedString {
                    if (entity.tags.isNotEmpty()) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_tag,
                            ).format(entity.tags.joinToString(", "))
                        )
                    }

                    if (entity.width != null && entity.height != null) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_size,
                            ).format(entity.width ?: 0, entity.height ?: 0)
                        )
                    }

                    if (entity.views != null) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_views,
                            ).format(entity.views ?: 0)
                        )
                    }

                    if (entity.downloads != null) {
                        append(
                            stringResource(
                                id = R.string.search_pixabay_downloads,
                            ).format(entity.downloads ?: 0)
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
                    }
                )
            }
        }
    }
}

@Composable
private fun PixabayExtendButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    extended: Boolean = false // true 펼침, false 닫힘 (기본값)
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = if (extended) {
                Icons.Filled.KeyboardArrowUp
            } else {
                Icons.Filled.KeyboardArrowDown
            },
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )
    }
}