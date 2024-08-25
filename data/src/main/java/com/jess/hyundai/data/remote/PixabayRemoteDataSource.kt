package com.jess.hyundai.data.remote

import com.jess.hyundai.data.BuildConfig
import com.jess.hyundai.data.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @see https://pixabay.com/api/docs
 */
interface PixabayRemoteDataSource {

    /**
     * 이미지 검색을 합니다.
     * https://pixabay.com/api/?key=36470409-07138ac710797ad8b9bb7dbf9&image_type=photo&q={검색어}&page={페이지}
     */
    @GET("api")
    suspend fun getImage(
        @Query("key") key: String = BuildConfig.PIXABAY_API_KEY,
        @Query("image_type") imageType: String = "photo",
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): PixabayResponse
}
