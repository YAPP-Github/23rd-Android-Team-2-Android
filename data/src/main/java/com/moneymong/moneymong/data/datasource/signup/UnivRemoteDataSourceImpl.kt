package com.moneymong.moneymong.data.datasource.signup

import com.moneymong.moneymong.network.api.signup.UniversityApi
import com.moneymong.moneymong.network.request.signup.SearchQueryRequest
import com.moneymong.moneymong.network.request.signup.UnivRequest
import com.moneymong.moneymong.network.response.signup.UnivResponse
import com.moneymong.moneymong.network.response.signup.UniversitiesReponse
import javax.inject.Inject

class UnivRemoteDataSourceImpl @Inject constructor(private val universityApi: UniversityApi) : UnivRemoteDataSource {
    override suspend fun userUniv(): Result<UnivResponse> {
        return universityApi.userUniv()
    }

    override suspend fun createUniv(body: UnivRequest): Result<Unit> {
        return universityApi.createUniv(body = body)
    }

    override suspend fun searchUniv(searchQuery: SearchQueryRequest): Result<UniversitiesReponse> {
        return universityApi.searchUniv(searchQuery.searchQuery)
    }
}