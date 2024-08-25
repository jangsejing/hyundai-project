package com.jess.hyundai.feature.detail.presentation.wikipedia

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WikipediaDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WikipediaDetailUiState.empty())
    val uiState: StateFlow<WikipediaDetailUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { prev ->
            prev.copy(
                title = "장세진",
                keywords = listOf(
                    "canonical",
                ),
                extract = "Ramsay Hunt syndrome type 2, commonly referred to simply as Ramsay Hunt syndrome (RHS) and also known as herpes zoster oticus, is inflammation of the geniculate ganglion of the facial nerve as a late consequence of varicella zoster virus (VZV). In regard to the frequency, less than 1% of varicella zoster infections involve the facial nerve and result in RHS. It is traditionally defined as a triad of ipsilateral facial paralysis, otalgia, and vesicles close to the ear and auditory canal. Due to its proximity to the vestibulocochlear nerve, the virus can spread and cause hearing loss, tinnitus, and vertigo. It is common for diagnoses to be overlooked or delayed, which can raise the likelihood of long-term consequences. It is more complicated than Bell's palsy. Therapy aims to shorten its overall length, while also providing pain relief and averting any consequences.",
                imageUrl = "https://pixabay.com/get/gcb9fbdd4863e02c911b8ed5fe1f171617027fcfd2b1ba3fb34624dec072e098e983c31190e5ea665e612cb3dc0e13d44bc2057fdeb66475fe9f99266eee79f98_1280.jpg",
                webUrl = "https://en.m.wikipedia.org/wiki/Ramsay_Hunt_syndrome_type_2"
            )
        }
    }

}