package com.jess.hyundai.feature.detail.presentation.pixabay

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PixabayDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PixabayDetailUiState.empty())
    val uiState: StateFlow<PixabayDetailUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { prev ->
            prev.copy(
                user = "장세진",
                tags = listOf(
                    "bar",
                    "ipad",
                    "mockup",
                    "bar",
                    "ipad",
                    "mockup",
                    "bar",
                    "ipad",
                    "mockup",
                    "bar",
                    "ipad",
                    "mockup",
                ),
                imageUrl = "https://pixabay.com/get/gcb9fbdd4863e02c911b8ed5fe1f171617027fcfd2b1ba3fb34624dec072e098e983c31190e5ea665e612cb3dc0e13d44bc2057fdeb66475fe9f99266eee79f98_1280.jpg"
            )
        }
    }

}