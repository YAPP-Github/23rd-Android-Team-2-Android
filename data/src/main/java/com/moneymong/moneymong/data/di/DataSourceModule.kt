package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.agency.AgencyLocalDataSource
import com.moneymong.moneymong.data.datasource.agency.AgencyLocalDataSourceImpl
import com.moneymong.moneymong.data.datasource.login.LoginLocalDataSource
import com.moneymong.moneymong.data.datasource.login.LoginLocalDataSourceImpl
import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSource
import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.login.TokenRemoteDataSource
import com.moneymong.moneymong.data.datasource.login.TokenRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.signup.UnivRemoteDataSource
import com.moneymong.moneymong.data.datasource.signup.UnivRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.agency.AgencyRemoteDataSource
import com.moneymong.moneymong.data.datasource.agency.AgencyRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.ledger.LedgerRemoteDataSource
import com.moneymong.moneymong.data.datasource.ledger.LedgerRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.ledgerdetail.LedgerDetailRemoteDataSource
import com.moneymong.moneymong.data.datasource.ledgerdetail.LedgerDetailRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.member.MemberRemoteDataSource
import com.moneymong.moneymong.data.datasource.member.MemberRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.ocr.OCRRemoteDataSource
import com.moneymong.moneymong.data.datasource.ocr.OCRRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.user.UserLocalDataSource
import com.moneymong.moneymong.data.datasource.user.UserLocalDataSourceImpl
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
    fun bindLoginDataSource(
        loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl
    ): LoginRemoteDataSource

    @Binds
    fun bindUnivDataSource(
        univRemoteDataSourceImpl: UnivRemoteDataSourceImpl
    ): UnivRemoteDataSource

    @Binds
    fun bindLoginLocalDataSource(
        loginLocalDataSourceImpl: LoginLocalDataSourceImpl
    ): LoginLocalDataSource

    @Binds
    fun bindTokenRemoteDataSource(
        tokenRemoteDataSourceImpl: TokenRemoteDataSourceImpl
    ): TokenRemoteDataSource

    @Binds
    fun bindAgencyRemoteDataSource(
        agencyRemoteDataSourceImpl: AgencyRemoteDataSourceImpl
    ): AgencyRemoteDataSource

    @Binds
    fun bindAgencyLocalDataSource(
        agencyLocalDataSourceImpl: AgencyLocalDataSourceImpl
    ): AgencyLocalDataSource

    @Binds
    fun bindUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Binds
    fun bindUserLocalDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource

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