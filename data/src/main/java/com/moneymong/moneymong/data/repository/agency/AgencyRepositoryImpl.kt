package com.moneymong.moneymong.data.repository.agency

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.moneymong.moneymong.data.datasource.agency.AgencyLocalDataSource
import com.moneymong.moneymong.data.datasource.agency.AgencyRemoteDataSource
import com.moneymong.moneymong.data.mapper.agency.toEntity
import com.moneymong.moneymong.data.mapper.agency.toRequest
import com.moneymong.moneymong.data.pagingsource.AgencyPagingSource
import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.entity.agency.AgencyJoinEntity
import com.moneymong.moneymong.domain.entity.agency.MyAgencyEntity
import com.moneymong.moneymong.domain.entity.agency.AgencyRegisterEntity
import com.moneymong.moneymong.domain.param.agency.AgencyJoinParam
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AgencyRepositoryImpl @Inject constructor(
    private val agencyRemoteDataSource: AgencyRemoteDataSource,
    private val agencyLocalDataSource: AgencyLocalDataSource
) : AgencyRepository {

    override suspend fun registerAgency(param: AgencyRegisterParam): Result<AgencyRegisterEntity> {
        return agencyRemoteDataSource.registerAgency(param.toRequest()).map { it.toEntity() }
    }

    override fun getAgencies(): Flow<PagingData<AgencyGetEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10),
            pagingSourceFactory = { AgencyPagingSource(agencyRemoteDataSource) }
        ).flow.map { pagingData ->
            pagingData.map { response ->
                response.toEntity()
            }
        }
    }

    override suspend fun fetchMyAgencyList(): Result<List<MyAgencyEntity>> =
        agencyRemoteDataSource.fetchMyAgencyList().map { it.map { it.toEntity() } }

    override suspend fun agencyCodeNumbers(
        agencyId: Long,
        data: AgencyJoinParam
    ): Result<AgencyJoinEntity> {
        return agencyRemoteDataSource.agencyCodeNumbers(agencyId, data.toRequest())
            .map { it.toEntity() }
    }

    override suspend fun saveAgencyId(agencyId: Int) =
        agencyLocalDataSource.saveAgencyId(agencyId = agencyId)

    override suspend fun fetchAgencyId(): Int =
        agencyLocalDataSource.fetchAgencyId()
}