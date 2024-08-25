package com.jess.hyundai.domain.usecase

import com.jess.hyundai.domain.model.WikipediaEntity
import com.jess.hyundai.domain.repository.WikipediaRepository
import javax.inject.Inject

class WikipediaGetRelatedPageUseCase @Inject constructor(
    private val repository: WikipediaRepository
) {

    suspend operator fun invoke(
        query: String,
    ): WikipediaEntity = repository.getRelatedPage(
        query = query,
    )
}