package com.jess.hyundai.feature.search.presentation

data class SearchResultUiState(
    val query: String?,
    val page: Int,
    val items: List<SearchResultItem>,
    val firstPage: Boolean,
    val loading: Boolean,
) {

    companion object {

        fun empty(
            query: String? = null,
            page: Int = 1,
            items: List<SearchResultItem> = emptyList(),
            firstPage: Boolean = false,
            loading: Boolean = false,
        ) = SearchResultUiState(
            query = query,
            page = page,
            items = items,
            firstPage = firstPage,
            loading = loading,
        )
    }
}