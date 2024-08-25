package com.jess.hyundai.data.repository

import com.jess.hyundai.data.model.toEntity
import com.jess.hyundai.data.remote.PixabayRemoteDataSource
import com.jess.hyundai.model.entity.PixabayEntity
import com.jess.hyundai.domain.repository.PixabayRepository
import javax.inject.Inject

class PixabayRepositoryImpl @Inject constructor(
    private val remoteDataSource: PixabayRemoteDataSource,
) : PixabayRepository {

    override suspend fun getImage(
        query: String,
        page: Int,
        perPage: Int,
    ): PixabayEntity = remoteDataSource.getImage(
        query = query,
        page = page,
        perPage = perPage,
    ).toEntity()
}