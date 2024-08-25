package com.jess.hyundai.navigator

import com.jess.hyundai.domain.model.PixabayHitEntity
import com.jess.hyundai.domain.model.WikipediaPageEntity

sealed interface Direction {

    data class SearchResult(
        val query: String,
    ) : Direction

    data class PixabayDetail(
        val entity: PixabayHitEntity,
    ) : Direction

    data class WikipediaDetail(
        val entity: WikipediaPageEntity,
    ) : Direction
}