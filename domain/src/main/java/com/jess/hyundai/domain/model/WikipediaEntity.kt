package com.jess.hyundai.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WikipediaEntity(
    val pages: List<WikipediaPageEntity>,
)

@Parcelize
data class WikipediaPageEntity(
    val id: Long?,
    val title: String?,
    val keyword: String?,
    val extract: String?,
    val imageUrl: String?,
    val webUrl: String?,
) : Parcelable