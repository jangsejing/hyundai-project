package com.jess.hyundai.navigator

import com.jess.hyundai.model.entity.PixabayHitEntity
import com.jess.hyundai.model.entity.WikipediaPageEntity

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
