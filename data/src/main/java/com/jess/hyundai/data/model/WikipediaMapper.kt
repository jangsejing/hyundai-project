package com.jess.hyundai.data.model

import com.jess.hyundai.domain.model.WikipediaEntity
import com.jess.hyundai.domain.model.WikipediaPageEntity

internal fun WikipediaResponse.toEntity() = WikipediaEntity(
    pages = pages?.map {
        it.toEntity()
    }.orEmpty()
)

internal fun WikipediaPageResponse.toEntity() = WikipediaPageEntity(
    id = pageId,
    title = title,
    keyword = titles?.canonical,
    extract = extract,
    imageUrl = originalImage?.source,
    webUrl = contentUrls?.mobile?.page,
)