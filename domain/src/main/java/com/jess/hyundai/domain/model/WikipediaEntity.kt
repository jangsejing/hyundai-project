package com.jess.hyundai.domain.model

data class WikipediaEntity(
    val pages: List<WikipediaPageEntity>,
)

data class WikipediaPageEntity(
    val id: Long?,
    val title: String?,
    val keyword: String?,
    val extract: String?,
    val imageUrl: String?,
    val webUrl: String?,
)