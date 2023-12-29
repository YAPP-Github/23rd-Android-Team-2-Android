package com.moneymong.moneymong.data.repository

import com.moneymong.moneymong.data.mapper.toRequest
import com.moneymong.moneymong.domain.param.AgencyRegisterParams
import com.moneymong.moneymong.domain.repository.AgencyRepository
import com.moneymong.moneymong.network.source.AgencyRemoteDataSource
import javax.inject.Inject

class AgencyRepositoryImpl @Inject constructor(
    private val agencyRemoteDataSource: AgencyRemoteDataSource
) : AgencyRepository {

    override suspend fun registerAgency(params: AgencyRegisterParams): Result<Unit> {
        return agencyRemoteDataSource.registerAgency(params.toRequest())
    }
}