package com.moneymong.moneymong.network.source.mock

import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.source.AgencyRemoteDataSource
import javax.inject.Inject

class AgencyRemoteDataSourceMock @Inject constructor() : AgencyRemoteDataSource {

    override suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit> {
        return Result.success(Unit)
    }
}