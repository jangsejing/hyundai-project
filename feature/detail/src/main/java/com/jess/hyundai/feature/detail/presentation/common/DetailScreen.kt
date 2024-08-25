package com.jess.hyundai.feature.detail.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jess.hyundai.feature.detail.R

@Composable
internal fun DetailBasicItem(
    title: String,
    content: String?,
    modifier: Modifier = Modifier,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    Row(
        modifier = modifier.padding(
            horizontal = 24.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.width(68.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = content.orEmpty(),
            overflow = overflow,
            maxLines = maxLines,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DetailTagsItem(
    tags: List<String>,
    onTagClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(
            horizontal = 24.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.width(68.dp),
            text = stringResource(id = R.string.pixabay_detail_tag),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.width(4.dp))

        FlowRow {
            tags.forEach {
                Text(
                    modifier = Modifier.clickable {
                        onTagClick(it)
                    },
                    text = stringResource(
                        id = R.string.pixabay_detail_tag_content,
                    ).format(it),
                    color = Color.Blue,
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun DetailDivider(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(2.dp))
    }
}
