package com.moneymong.moneymong.data.datasource.mock

import com.moneymong.moneymong.data.datasource.AgencyRemoteDataSource
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import javax.inject.Inject

class AgencyRemoteDataSourceMock @Inject constructor() : AgencyRemoteDataSource {

    override suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit> {
        return Result.success(Unit)
    }
}