package com.jess.hyundai.domain.usecase

import com.jess.hyundai.domain.model.PixabayEntity
import com.jess.hyundai.domain.repository.PixabayRepository
import javax.inject.Inject

class PixabayGetImageUseCase @Inject constructor(
    private val repository: PixabayRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int,
        perPage: Int,
    ): PixabayEntity = repository.getImage(
        query = query,
        page = page,
        perPage = perPage,
    )
}