package com.jess.hyundai.feature.search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.hyundai.domain.usecase.PixabayGetImageUseCase
import com.jess.hyundai.domain.usecase.WikipediaGetRelatedPageUseCase
import com.jess.hyundai.feature.search.presentation.SearchResultActivity.Companion.EXTRA_SEARCH_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPixabayImage: PixabayGetImageUseCase,
    private val getWikipediaRelatedPage: WikipediaGetRelatedPageUseCase,
) : ViewModel() {

    private val query get() = savedStateHandle.get<String>(EXTRA_SEARCH_QUERY)

    private val _uiState = MutableStateFlow(SearchResultUiState.empty())
    val uiState: StateFlow<SearchResultUiState> = _uiState.asStateFlow()

    // 페이징 정보
    private var nextPage: Int = 1
    private var finishedPage: Boolean = false

    init {
        onSearch(query)
    }

    fun onSearch(
        query: String?,
    ) {
        if (query.isNullOrBlank()) {
            return
        }

        // 페이지 정보 초기화
        nextPage = 1
        finishedPage = false

        requestItems(
            query = query,
            firstPage = true,
        )
    }

    /**
     * query 를 이용하여 이미지 검색을 시도합니다.
     */
    private fun requestItems(
        query: String,
        firstPage: Boolean = false,
    ) = viewModelScope.launch {
        if (query.isBlank()) {
            return@launch
        }

        _uiState.update { prev ->
            prev.copy(
                loading = true,
            )
        }

        kotlin.runCatching {
            val wikipedia = createWikipediaItems(query)
            val pixabay = createPixabayItems(query)

            // pixabay 가 있을 경우 첫번째 index 에 wikipedia 세팅
            val list = pixabay.toMutableList()
            if (wikipedia != null && list.isNotEmpty()) {
                list.add(1, wikipedia)
            }

            _uiState.update { prev ->
                // 화면에 노출할 리스트 정보
                val items = if (firstPage) {
                    list
                } else {
                    prev.items.toMutableList().apply {
                        addAll(list)
                    }
                }

                prev.copy(
                    state = if (items.isNotEmpty()) {
                        SearchResultContentUiState.Succeeded
                    } else {
                        SearchResultContentUiState.Empty
                    },
                    query = query,
                    items = items.toImmutableList(),
                    loading = false,
                )
            }
        }.onFailure {
            Timber.e(it)
            _uiState.update { prev ->
                prev.copy(
                    state = SearchResultContentUiState.Failed(
                        it.message.toString(),
                    ),
                    loading = false,
                )
            }
        }
    }

    /**
     * total, perPage 를 이용하여 전체 페이지 갯수를 계산하고
     * 다음 페이지를 ++ 합니다.
     */
    private fun calculateNextAndFinishedPage(
        total: Int,
    ) {
        val perPage = SearchResultConstant.PER_PAGE
        val totalPage = when {
            // total : 20, perPage : 20 -> 1
            // total : 2000, perPage : 20 -> 100
            total % perPage == 0 -> total / perPage

            // total : 10, perPage : 20 -> 1
            // total : 2000, perPage : 20 -> 100
            else -> (total / perPage) + 1
        }.toInt()

        // 다음 정보를 위해 미리 계산함
        nextPage++
        finishedPage = nextPage > totalPage
    }

    /**
     * Pixabay Item 를 생성합니다.
     */
    private suspend fun createPixabayItems(
        query: String,
    ): List<SearchResultItem> {
        return kotlin.runCatching {
            val response = getPixabayImage(
                query = query,
                page = nextPage,
                perPage = SearchResultConstant.PER_PAGE,
            )

            // 다음 페이지 계산
            calculateNextAndFinishedPage(response.total)

            // 결과
            response.hits.map {
                SearchResultItem.PixabayItem(it)
            }
        }.onFailure {
            Timber.e(it)
        }.getOrDefault(
            emptyList(),
        )
    }

    /**
     * 위키 아이템을 생성하고 list 중 3개만 추출합니다.
     */
    private suspend fun createWikipediaItems(
        query: String,
    ): SearchResultItem.WikipediaItem? {
        return kotlin.runCatching {
            if (nextPage > 1) {
                emptyList()
            } else {
                // 3개 까지 추출함
                getWikipediaRelatedPage(
                    query = query,
                ).pages.take(3)
            }
        }.onFailure {
            Timber.e(it)
        }.getOrDefault(
            emptyList(),
        ).takeIf {
            it.isNotEmpty()
        }?.let { items ->
            SearchResultItem.WikipediaItem(items.toImmutableList())
        }
    }

    /**
     * 다음 페이지를 로드합니다.
     */
    fun onLoadNextPage() {
        // uiState 의 정보를 가져와 로드함
        val value = uiState.value
        if (value.loading || finishedPage) {
            return
        }
        requestItems(value.query.orEmpty())
    }
}
