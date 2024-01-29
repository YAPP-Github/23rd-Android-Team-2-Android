package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.agency.AgencyRemoteDataSource
import com.moneymong.moneymong.data.datasource.impl.AgencyRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.ledger.LedgerRemoteDataSource
import com.moneymong.moneymong.data.datasource.ledger.LedgerRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.ledgerdetail.LedgerDetailRemoteDataSource
import com.moneymong.moneymong.data.datasource.ledgerdetail.LedgerDetailRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.member.MemberRemoteDataSource
import com.moneymong.moneymong.data.datasource.member.MemberRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.ocr.OCRRemoteDataSource
import com.moneymong.moneymong.data.datasource.ocr.OCRRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.user.UserRemoteDataSource
import com.moneymong.moneymong.data.datasource.user.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindAgencyRemoteDataSource(
        agencyRemoteDataSourceImpl: AgencyRemoteDataSourceImpl
    ): AgencyRemoteDataSource

    @Binds
    fun bindUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Binds
    fun provideOCRRemoteDataSource(
        ocrRemoteDataSourceImpl: OCRRemoteDataSourceImpl
    ): OCRRemoteDataSource

    @Binds
    fun provideLedgerRemoteDataSource(
        ledgerRemoteDataSourceImpl: LedgerRemoteDataSourceImpl
    ): LedgerRemoteDataSource

    @Binds
    fun provideMemberRemoteDataSource(
        memberRemoteDataSourceImpl: MemberRemoteDataSourceImpl
    ): MemberRemoteDataSource

    @Binds
    fun provideLedgerDetailRemoteDataSource(
        ledgerDetailRemoteDataSourceImpl: LedgerDetailRemoteDataSourceImpl
    ): LedgerDetailRemoteDataSource
}