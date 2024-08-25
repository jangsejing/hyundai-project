package com.jess.hyundai.feature.search.presentation

import com.jess.hyundai.domain.model.PixabayHitEntity
import com.jess.hyundai.domain.model.WikipediaPageEntity

sealed interface SearchResultItem {

    data class WikipediaItem(
        val entities: List<WikipediaPageEntity>,
    ) : SearchResultItem

    data class PixabayItem(
        val entity: PixabayHitEntity,
    ) : SearchResultItem
}