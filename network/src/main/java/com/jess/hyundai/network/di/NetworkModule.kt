package com.jess.hyundai.network.di

import com.jess.hyundai.data.remote.PixabayRemoteDataSource
import com.jess.hyundai.data.remote.WikipediaRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitForWikipedia

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitForPixabay

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    // 요청과 응답의 본문 내용까지 로그에 포함
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @RetrofitForPixabay
    fun provideRetrofitForPixabay(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @RetrofitForWikipedia
    fun provideRetrofitForWikipedia(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePixabayRemoteDataSource(
        @RetrofitForPixabay retrofit: Retrofit,
    ): PixabayRemoteDataSource {
        return retrofit.create(PixabayRemoteDataSource::class.java)
    }

    @Provides
    fun provideWikipediaRemoteDataSource(
        @RetrofitForWikipedia retrofit: Retrofit,
    ): WikipediaRemoteDataSource {
        return retrofit.create(WikipediaRemoteDataSource::class.java)
    }

}