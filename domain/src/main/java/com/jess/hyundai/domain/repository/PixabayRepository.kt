package com.jess.hyundai.domain.repository

import com.jess.hyundai.model.entity.PixabayEntity

interface PixabayRepository {

    suspend fun getImage(
        query: String,
        page: Int,
        perPage: Int,
    ): PixabayEntity
}
