package com.jess.hyundai.feature.detail.presentation.wikipedia

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jess.hyundai.domain.model.WikipediaPageEntity
import com.jess.hyundai.feature.detail.presentation.wikipedia.WikipediaDetailActivity.Companion.EXTRA_WIKIPEDIA_ENTITY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WikipediaDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val entity get() = savedStateHandle.get<WikipediaPageEntity>(EXTRA_WIKIPEDIA_ENTITY)

    private val _uiState = MutableStateFlow(WikipediaDetailUiState.empty())
    val uiState: StateFlow<WikipediaDetailUiState> = _uiState.asStateFlow()

    init {
        createWikipediaDetail()
    }

    private fun createWikipediaDetail() {
        entity?.let { entity ->
            _uiState.update { prev ->
                prev.copy(
                    title = entity.title,
                    keywords = listOf(entity.keyword.orEmpty()),
                    extract = entity.extract,
                    imageUrl = entity.imageUrl,
                    webUrl = entity.webUrl,
                )
            }
        }
    }

}