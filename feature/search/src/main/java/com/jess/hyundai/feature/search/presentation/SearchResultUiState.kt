package com.jess.hyundai.feature.search.presentation

/**
 * @property state 화면의 상태
 * @property query 검색어
 * @property items 화면에 노출할 아이템
 * @property firstPage 첫 페이지 여부
 * @property loading 로딩중
 */
data class SearchResultUiState(
    val state: SearchResultContentUiState,
    val query: String?,
    val items: List<SearchResultItem>,
    val firstPage: Boolean,
    val loading: Boolean,
) {

    companion object {

        fun empty(
            state: SearchResultContentUiState = SearchResultContentUiState.None,
            query: String? = null,
            items: List<SearchResultItem> = emptyList(),
            firstPage: Boolean = false,
            loading: Boolean = false,
        ) = SearchResultUiState(
            state = state,
            query = query,
            items = items,
            firstPage = firstPage,
            loading = loading,
        )
    }
}

sealed interface SearchResultContentUiState {
    data object None : SearchResultContentUiState
    data object Succeeded : SearchResultContentUiState
    data class Failed(val message: String) : SearchResultContentUiState
    data object Empty : SearchResultContentUiState
}
