package com.jess.hyundai.feature.search.presentation.di

import com.jess.hyundai.data.repository.PixabayRepositoryImpl
import com.jess.hyundai.data.repository.WikipediaRepositoryImpl
import com.jess.hyundai.domain.repository.PixabayRepository
import com.jess.hyundai.domain.repository.WikipediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchResultViewModelComponent {

    @Binds
    abstract fun bindPixabayRepository(
        repository: PixabayRepositoryImpl,
    ): PixabayRepository

    @Binds
    abstract fun bindWikipediaRepository(
        repository: WikipediaRepositoryImpl,
    ): WikipediaRepository
}
