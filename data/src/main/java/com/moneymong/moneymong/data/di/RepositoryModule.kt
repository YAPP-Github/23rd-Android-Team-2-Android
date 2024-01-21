package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.repository.ledger.LedgerRepositoryImpl
import com.moneymong.moneymong.data.repository.ocr.OCRRepositoryImpl
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
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

    @Binds
    fun provideLedgerRepository(
        ledgerRepositoryImpl: LedgerRepositoryImpl
    ): LedgerRepository
}