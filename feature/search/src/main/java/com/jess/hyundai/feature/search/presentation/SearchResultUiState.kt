package com.jess.hyundai.feature.search.presentation

data class SearchResultUiState(
    val query: String?,
    val page: Int,
    val items: List<SearchResultItem>,
    val loading: Boolean,
) {

    companion object {

        fun empty(
            query: String? = null,
            page: Int = 1,
            items: List<SearchResultItem> = emptyList(),
            loading: Boolean = false,
        ) = SearchResultUiState(
            query = query,
            page = page,
            items = items,
            loading = loading,
        )
    }
}