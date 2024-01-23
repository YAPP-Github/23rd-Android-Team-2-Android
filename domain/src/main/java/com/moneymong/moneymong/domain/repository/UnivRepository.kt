package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.entity.signup.UniversitiesEntity
import com.moneymong.moneymong.domain.param.signup.SearchQueryParam
import com.moneymong.moneymong.domain.param.signup.UnivParam

interface UnivRepository {
    suspend fun createUniv(body: UnivParam) : Result<Unit>

    suspend fun searchUniv(searchQuery: SearchQueryParam) : Result<UniversitiesEntity>
}