package com.jess.hyundai.data.model

import com.google.gson.annotations.SerializedName

class WikipediaResponse(
    @SerializedName("pages") val pages: List<WikipediaPageResponse>?,
)

/**
 * @property pageId 페이지 아이디
 * @property title 타이틀
 * @property title 키워드 (재검색 키워드)
 * @property extract 상세 정보
 * @property originalImage 이미지 URL
 * @property contentUrls 웹뷰 URL
 */
class WikipediaPageResponse(
    @SerializedName("pageid") val pageId: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("titles") val titles: WikipediaTitlesResponse?,
    @SerializedName("extract") val extract: String?,
    @SerializedName("originalimage") val originalImage: WikipediaOriginalImageResponse?,
    @SerializedName("content_urls") val contentUrls: WikipediaContentUrlsResponse?,
)

class WikipediaTitlesResponse(
    @SerializedName("canonical") val canonical: String?,
)

class WikipediaOriginalImageResponse(
    @SerializedName("source") val source: String?,
)

class WikipediaContentUrlsResponse(
    @SerializedName("mobile") val mobile: Mobile?,
) {
    data class Mobile(
        @SerializedName("page") val page: String?,
    )
}
