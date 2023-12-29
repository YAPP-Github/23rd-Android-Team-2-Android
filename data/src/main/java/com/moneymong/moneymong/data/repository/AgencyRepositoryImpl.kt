package com.moneymong.moneymong.data.repository

import com.moneymong.moneymong.data.mapper.toRequest
import com.moneymong.moneymong.domain.param.AgencyRegisterParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import com.moneymong.moneymong.network.source.AgencyRemoteDataSource
import javax.inject.Inject

class AgencyRepositoryImpl @Inject constructor(
    private val agencyRemoteDataSource: AgencyRemoteDataSource
) : AgencyRepository {

    override suspend fun registerAgency(param: AgencyRegisterParam): Result<Unit> {
        return agencyRemoteDataSource.registerAgency(param.toRequest())
    }
}