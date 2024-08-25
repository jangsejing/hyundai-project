package com.jess.hyundai.data.remote

import com.jess.hyundai.data.model.WikipediaResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @see https://en.wikipedia.org/api/rest_v1/
 */
interface WikipediaRemoteDataSource {

    /**
     * 검색어와 관련된 페이지정보를 가져옵니다.
     */
    @GET("api/rest_v1/page/related/{query}")
    suspend fun getRelatedPage(
        @Path("query") query: String,
    ): WikipediaResponse
}
