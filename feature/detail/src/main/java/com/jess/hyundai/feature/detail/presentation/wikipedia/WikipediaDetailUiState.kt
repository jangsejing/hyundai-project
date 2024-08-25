package com.jess.hyundai.feature.detail.presentation.wikipedia

data class WikipediaDetailUiState(
    val title: String?,
    val keywords: List<String>,
    val extract: String?,
    val imageUrl: String?,
    val webUrl: String?,
) {

    companion object {

        fun empty(
            title: String? = null,
            keywords: List<String> = emptyList(),
            extract: String? = null,
            imageUrl: String? = null,
            webUrl: String? = null,
        ) = WikipediaDetailUiState(
            title = title,
            keywords = keywords,
            extract = extract,
            imageUrl = imageUrl,
            webUrl = webUrl,
        )
    }
}