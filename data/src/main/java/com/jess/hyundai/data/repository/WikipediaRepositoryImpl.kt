package com.jess.hyundai.data.repository

import com.jess.hyundai.data.model.toEntity
import com.jess.hyundai.data.remote.WikipediaRemoteDataSource
import com.jess.hyundai.domain.repository.WikipediaRepository
import com.jess.hyundai.model.entity.WikipediaEntity
import javax.inject.Inject

class WikipediaRepositoryImpl @Inject constructor(
    private val remoteDataSource: WikipediaRemoteDataSource,
) : WikipediaRepository {

    override suspend fun getRelatedPage(
        query: String,
    ): WikipediaEntity = remoteDataSource.getRelatedPage(
        query = query,
    ).toEntity()
}
