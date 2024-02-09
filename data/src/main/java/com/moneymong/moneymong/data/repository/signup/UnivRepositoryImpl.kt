package com.moneymong.moneymong.data.repository.signup

import com.moneymong.moneymong.data.datasource.signup.UnivRemoteDataSource
import com.moneymong.moneymong.data.mapper.signup.toEntity
import com.moneymong.moneymong.data.mapper.signup.toRequest
import com.moneymong.moneymong.domain.entity.signup.UnivEntity
import com.moneymong.moneymong.domain.entity.signup.UniversitiesEntity
import com.moneymong.moneymong.domain.param.signup.SearchQueryParam
import com.moneymong.moneymong.domain.param.signup.UnivParam
import com.moneymong.moneymong.domain.repository.UnivRepository
import javax.inject.Inject

class UnivRepositoryImpl @Inject constructor(
    private val univRemoteDataSource: UnivRemoteDataSource
) : UnivRepository {
    override suspend fun createUniv(body: UnivParam): Result<Unit> {
        return univRemoteDataSource.createUniv(body.toRequest())
    }

    override suspend fun searchUniv(searchQuery: SearchQueryParam): Result<UniversitiesEntity> {
        return univRemoteDataSource.searchUniv(searchQuery.toRequest()).map { it.toEntity() }
    }
}