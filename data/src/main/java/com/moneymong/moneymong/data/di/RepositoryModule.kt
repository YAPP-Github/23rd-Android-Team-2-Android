package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.repository.OCRRepositoryImpl
import com.moneymong.moneymong.domain.repository.ocr.OCRRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideOCRRepository(
        ocrRepositoryImpl: OCRRepositoryImpl
    ): OCRRepository
}