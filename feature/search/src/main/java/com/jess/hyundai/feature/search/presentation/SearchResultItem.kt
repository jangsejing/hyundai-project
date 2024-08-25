package com.jess.hyundai.feature.search.presentation

import com.jess.hyundai.model.entity.PixabayHitEntity
import com.jess.hyundai.model.entity.WikipediaPageEntity

sealed interface SearchResultItem {

    data class WikipediaItem(
        val entities: List<WikipediaPageEntity>,
    ) : SearchResultItem

    data class PixabayItem(
        val entity: PixabayHitEntity,
    ) : SearchResultItem
}
