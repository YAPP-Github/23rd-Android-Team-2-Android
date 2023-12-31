package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.ocr.OCRRemoteDataSource
import com.moneymong.moneymong.data.datasource.ocr.OCRRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun provideOCRRemoteDataSource(
        ocrRemoteDataSourceImpl: OCRRemoteDataSourceImpl
    ): OCRRemoteDataSource
}