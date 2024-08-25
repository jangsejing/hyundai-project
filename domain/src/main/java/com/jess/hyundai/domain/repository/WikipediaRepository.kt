package com.jess.hyundai.domain.repository

import com.jess.hyundai.model.entity.WikipediaEntity

interface WikipediaRepository {

    suspend fun getRelatedPage(
        query: String,
    ): WikipediaEntity
}
