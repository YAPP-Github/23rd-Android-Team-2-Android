package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.network.api.AccessTokenApi
import com.moneymong.moneymong.network.request.login.RefreshTokenRequest
import com.moneymong.moneymong.network.response.login.RefreshTokenResponse
import javax.inject.Inject
import javax.inject.Provider

class TokenRemoteDataSourceImpl @Inject constructor(
    private val accessTokenApiProvider: Provider<AccessTokenApi>
) : TokenRemoteDataSource {
    private val accessTokenApi by lazy { accessTokenApiProvider.get() }

    override suspend fun getUpdateToken(refreshToken: String): Result<RefreshTokenResponse> {
        return accessTokenApi.refreshTokenApi(RefreshTokenRequest(refreshToken))
    }

    override suspend fun deleteRefreshToken(body: RefreshTokenRequest) {
        return accessTokenApi.deleteRefreshToken(body)
    }
}