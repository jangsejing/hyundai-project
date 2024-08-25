package com.jess.hyundai.domain.model

class PixabayEntity(
    val total: Int,
    val totalHits: Int,
    val hits: List<PixabayHitEntity>,
)

data class PixabayHitEntity(
    val id: Long?,
    val user: String?,
    val tags: List<String>,
    val width: Int?,
    val height: Int?,
    val views: Long?,
    val downloads: Long?,
    val imageUrl: String?
)
