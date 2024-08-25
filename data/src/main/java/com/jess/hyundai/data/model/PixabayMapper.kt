package com.jess.hyundai.data.model

import com.jess.hyundai.domain.model.PixabayEntity
import com.jess.hyundai.domain.model.PixabayHitEntity

internal fun PixabayResponse.toEntity() = PixabayEntity(
    total = total ?: 0,
    totalHits = totalHits ?: 0,
    hits = hits?.map {
        it.toEntity()
    }.orEmpty(),
)

internal fun PixabayHitResponse.toEntity() = PixabayHitEntity(
    id = id,
    user = user,
    tags = tags?.split(",").orEmpty(),
    width = imageWidth,
    height = imageHeight,
    views = views,
    downloads = downloads,
    imageUrl = largeImageUrl,
)