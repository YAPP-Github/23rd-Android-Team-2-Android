package com.moneymong.moneymong.data.datasource.signup

import com.moneymong.moneymong.network.request.signup.SearchQueryRequest
import com.moneymong.moneymong.network.request.signup.UnivRequest
import com.moneymong.moneymong.network.response.signup.UnivResponse
import com.moneymong.moneymong.network.response.signup.UniversitiesReponse

interface UnivRemoteDataSource {
    suspend fun createUniv(body: UnivRequest) : Result<Unit>

    suspend fun searchUniv(searchQuery : SearchQueryRequest) : Result<UniversitiesReponse>
}