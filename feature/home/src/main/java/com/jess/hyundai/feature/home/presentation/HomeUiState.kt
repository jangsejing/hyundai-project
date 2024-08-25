package com.jess.hyundai.feature.home.presentation

data class HomeUiState(
    val query: String,
) {

    companion object {

        fun empty(
            query: String = "",
        ) = HomeUiState(
            query = query
        )
    }
}