package com.moneymong.moneymong.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.moneymong.moneymong.data.datasource.AgencyRemoteDataSource
import com.moneymong.moneymong.data.mapper.agency.toEntity
import com.moneymong.moneymong.data.mapper.agency.toRequest
import com.moneymong.moneymong.data.pagingsource.AgencyPagingSource
import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AgencyRepositoryImpl @Inject constructor(
    private val agencyRemoteDataSource: AgencyRemoteDataSource
) : AgencyRepository {

    override suspend fun registerAgency(param: AgencyRegisterParam): Result<Unit> {
        return agencyRemoteDataSource.registerAgency(param.toRequest())
    }

    override fun getAgencies(): Flow<PagingData<AgencyGetEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { AgencyPagingSource(agencyRemoteDataSource) }
        ).flow.map { pagingData ->
            pagingData.map { response ->
                response.toEntity()
            }
        }
    }
}