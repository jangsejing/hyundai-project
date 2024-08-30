package com.jess.hyundai.model.entity

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
data class WikipediaEntity(
    val pages: List<WikipediaPageEntity>,
)

@Immutable
@Parcelize
data class WikipediaPageEntity(
    val id: Long?,
    val title: String?,
    val keyword: String?,
    val extract: String?,
    val imageUrl: String?,
    val webUrl: String?,
) : Parcelable
