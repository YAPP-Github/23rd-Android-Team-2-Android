package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.repository.ledger.LedgerRepositoryImpl
import com.moneymong.moneymong.data.repository.ledgerdetail.LedgerDetailRepositoryImpl
import com.moneymong.moneymong.data.repository.member.MemberRepositoryImpl
import com.moneymong.moneymong.data.repository.ocr.OCRRepositoryImpl
import com.moneymong.moneymong.data.repository.user.UserRepositoryImpl
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import com.moneymong.moneymong.domain.repository.ocr.OCRRepository
import com.moneymong.moneymong.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    fun provideOCRRepository(
        ocrRepositoryImpl: OCRRepositoryImpl
    ): OCRRepository

    @Binds
    fun provideLedgerRepository(
        ledgerRepositoryImpl: LedgerRepositoryImpl
    ): LedgerRepository

    @Binds
    fun provideLedgerDetailRepository(
        ledgerDetailRepositoryImpl: LedgerDetailRepositoryImpl
    ): LedgerDetailRepository

    @Binds
    fun provideMemberRepository(
        memberRepositoryImpl: MemberRepositoryImpl
    ): MemberRepository
}