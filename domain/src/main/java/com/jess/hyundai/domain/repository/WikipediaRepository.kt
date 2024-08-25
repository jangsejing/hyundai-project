package com.jess.hyundai.domain.repository

import com.jess.hyundai.domain.model.WikipediaEntity

interface WikipediaRepository {

    suspend fun getRelatedPage(
        query: String,
    ): WikipediaEntity

}