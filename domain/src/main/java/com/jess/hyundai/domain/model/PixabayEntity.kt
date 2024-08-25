package com.jess.hyundai.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class PixabayEntity(
    val total: Int,
    val totalHits: Int,
    val hits: List<PixabayHitEntity>,
)

@Parcelize
data class PixabayHitEntity(
    val id: Long?,
    val user: String?,
    val tags: List<String>,
    val width: Int?,
    val height: Int?,
    val views: Long?,
    val downloads: Long?,
    val imageUrl: String?
) : Parcelable
