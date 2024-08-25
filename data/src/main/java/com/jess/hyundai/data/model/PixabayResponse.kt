package com.jess.hyundai.data.model

import com.google.gson.annotations.SerializedName

data class PixabayResponse(
    @SerializedName("total") val total: Int?,
    @SerializedName("totalHits") val totalHits: Int?,
    @SerializedName("hits") val hits: List<PixabayHitResponse>?,
)

/**
 * @property id 이미지 아이디
 * @property user 사용자 이름
 * @property tags 태그
 * @property imageWidth 이미지 사이즈
 * @property imageHeight 이미지 사이즈
 * @property views 조회
 * @property downloads 다운로드 횟수
 * @property largeImageUrl 이미지 URL
 */
data class PixabayHitResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("user") val user: String?,
    @SerializedName("tags") val tags: String?,
    @SerializedName("imageWidth") val imageWidth: Int?,
    @SerializedName("imageHeight") val imageHeight: Int?,
    @SerializedName("views") val views: Long?,
    @SerializedName("downloads") val downloads: Long?,
    @SerializedName("largeImageURL") val largeImageUrl: String?
)