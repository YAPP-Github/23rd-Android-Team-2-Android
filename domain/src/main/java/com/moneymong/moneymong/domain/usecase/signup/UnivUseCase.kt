package com.moneymong.moneymong.domain.usecase.signup

import com.moneymong.moneymong.domain.entity.signup.UniversitiesEntity
import com.moneymong.moneymong.domain.param.signup.SearchQueryParam
import com.moneymong.moneymong.domain.param.signup.UnivParam
import com.moneymong.moneymong.domain.repository.UnivRepository
import javax.inject.Inject

class UnivUseCase @Inject constructor(
    private val univRepository: UnivRepository
){
    suspend fun createUniv(body : UnivParam) : Result<Unit>{
        return univRepository.createUniv(body)
    }

    suspend fun searchUniv(searchQuery : String) : Result<UniversitiesEntity> {
        return univRepository.searchUniv(SearchQueryParam(searchQuery))
    }
}