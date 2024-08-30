package com.jess.hyundai.model.entity

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
class PixabayEntity(
    val total: Int,
    val totalHits: Int,
    val hits: List<PixabayHitEntity>,
)

@Immutable
@Parcelize
data class PixabayHitEntity(
    val id: Long?,
    val user: String?,
    val tags: List<String>,
    val width: Int?,
    val height: Int?,
    val views: Long?,
    val downloads: Long?,
    val imageUrl: String?,
) : Parcelable
