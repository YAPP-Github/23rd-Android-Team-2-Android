package com.moneymong.moneymong.data.repository

import com.moneymong.moneymong.data.mapper.agency.toRequest
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import com.moneymong.moneymong.network.datasource.AgencyRemoteDataSource
import javax.inject.Inject

class AgencyRepositoryImpl @Inject constructor(
    private val agencyRemoteDataSource: AgencyRemoteDataSource
) : AgencyRepository {

    override suspend fun registerAgency(param: AgencyRegisterParam): Result<Unit> {
        return agencyRemoteDataSource.registerAgency(param.toRequest())
    }
}