package com.jess.hyundai.navigotor

import android.content.Context
import android.content.Intent
import com.jess.hyundai.feature.detail.presentation.pixabay.PixabayDetailActivity
import com.jess.hyundai.feature.detail.presentation.wikipedia.WikipediaDetailActivity
import com.jess.hyundai.feature.search.presentation.SearchResultActivity
import com.jess.hyundai.navigator.Direction
import com.jess.hyundai.navigator.Navigator

class NavigatorImpl : Navigator {

    override fun getIntent(
        context: Context,
        direction: Direction,
    ): Intent = direction.run {
        when (this) {
            is Direction.SearchResult -> {
                SearchResultActivity.newIntent(
                    context = context,
                    query = query,
                )
            }

            is Direction.WikipediaDetail -> {
                WikipediaDetailActivity.newIntent(
                    context = context,
                    entity = entity,
                )
            }

            is Direction.PixabayDetail -> {
                PixabayDetailActivity.newIntent(
                    context = context,
                    entity = entity,
                )
            }

        }
    }
}