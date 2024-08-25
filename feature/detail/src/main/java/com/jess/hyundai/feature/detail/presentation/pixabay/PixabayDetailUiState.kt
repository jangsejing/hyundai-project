package com.jess.hyundai.feature.detail.presentation.pixabay

data class PixabayDetailUiState(
    val user: String?,
    val tags: List<String>,
    val width: Int?,
    val height: Int?,
    val views: Long?,
    val downloads: Long?,
    val imageUrl: String?,
) {

    companion object {

        fun empty(
            user: String? = null,
            tags: List<String> = emptyList(),
            width: Int? = 0,
            height: Int? = 0,
            views: Long? = 0,
            downloads: Long? = 0,
            imageUrl: String? = null,
        ) = PixabayDetailUiState(
            user = user,
            tags = tags,
            width = width,
            height = height,
            views = views,
            downloads = downloads,
            imageUrl = imageUrl,
        )
    }
}
