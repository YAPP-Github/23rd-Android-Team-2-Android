package com.moneymong.moneymong.domain.repository

import androidx.paging.PagingData
import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.entity.agency.AgencyJoinEntity
import com.moneymong.moneymong.domain.entity.agency.MyAgencyEntity
import com.moneymong.moneymong.domain.entity.agency.AgencyRegisterEntity
import com.moneymong.moneymong.domain.param.agency.AgencyJoinParam
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import kotlinx.coroutines.flow.Flow

interface AgencyRepository {
    suspend fun registerAgency(param: AgencyRegisterParam): Result<AgencyRegisterEntity>
    fun getAgencies(): Flow<PagingData<AgencyGetEntity>>
    suspend fun fetchMyAgencyList(): Result<List<MyAgencyEntity>>
    suspend fun agencyCodeNumbers(agencyId: Long, data: AgencyJoinParam): Result<AgencyJoinEntity>

    suspend fun saveAgencyId(agencyId: Int)
    suspend fun fetchAgencyId(): Int
}