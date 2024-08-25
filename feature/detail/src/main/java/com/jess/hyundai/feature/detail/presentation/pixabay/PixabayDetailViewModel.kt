package com.jess.hyundai.feature.detail.presentation.pixabay

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jess.hyundai.model.entity.PixabayHitEntity
import com.jess.hyundai.feature.detail.presentation.pixabay.PixabayDetailActivity.Companion.EXTRA_PIXABAY_ENTITY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PixabayDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val entity get() = savedStateHandle.get<PixabayHitEntity>(EXTRA_PIXABAY_ENTITY)

    private val _uiState = MutableStateFlow(PixabayDetailUiState.empty())
    val uiState: StateFlow<PixabayDetailUiState> = _uiState.asStateFlow()

    init {
        createPixabayDetail()
    }

    private fun createPixabayDetail() {
        entity?.let { entity ->
            _uiState.update { prev ->
                prev.copy(
                    user = entity.user,
                    tags = entity.tags,
                    width = entity.width,
                    height = entity.height,
                    views = entity.views,
                    downloads = entity.downloads,
                    imageUrl = entity.imageUrl,
                )
            }
        }
    }
}