package com.jess.hyundai.feature.search.presentation.model

data class SearchResultPageModel(
    val nextPage: Int = 1,
    val finishedPage: Boolean = false,
) {

    fun plusNextPage(): SearchResultPageModel {
        val nextPage = nextPage
        return this.copy(
            nextPage = nextPage + 1
        )
    }
}