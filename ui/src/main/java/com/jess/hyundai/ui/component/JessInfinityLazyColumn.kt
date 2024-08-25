package com.jess.hyundai.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 무한 스크롤이 가능한 LazyColumn 입니다.
 */
@Composable
fun JessInfinityLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    loadMoreLimitCount: Int = 5,
    loadOnBottom: Boolean = true,
    onLoadMore: () -> Unit = {},
    content: LazyListScope.() -> Unit,
) {
    state.onLoadMore(
        limitCount = loadMoreLimitCount,
        action = onLoadMore,
        loadOnBottom = loadOnBottom,
    )

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        content = content,
    )
}

@SuppressLint("ComposableNaming")
@Composable
private fun LazyListState.onLoadMore(
    limitCount: Int,
    loadOnBottom: Boolean,
    action: () -> Unit
) {
    val reached by remember {
        derivedStateOf {
            reachedBottom(limitCount = limitCount, triggerOnEnd = loadOnBottom)
        }
    }
    LaunchedEffect(reached) {
        if (reached) {
            action()
        }
    }
}

private fun LazyListState.reachedBottom(
    limitCount: Int,
    triggerOnEnd: Boolean,
): Boolean {
    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return (triggerOnEnd && lastVisibleItem?.index == layoutInfo.totalItemsCount - 1)
            || lastVisibleItem?.index != 0 && lastVisibleItem?.index == layoutInfo.totalItemsCount - (limitCount + 1)
}