package com.moneymong.moneymong.network.util

import com.moneymong.moneymong.domain.repository.TokenRepository
import com.moneymong.moneymong.network.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class MoneyMongTokenAuthenticator @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val tokenCallback: TokenCallback
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val isPathRefresh =
            response.request.url.toString() == BuildConfig.MONEYMONG_BASE_URL + "api/v1/tokens"
        if (response.code == 401 && !isPathRefresh) {
            return runBlocking {
                var newRequest: Request? = null
                tokenRepository.getRefreshToken().onSuccess {
                    tokenRepository.getUpdateToken(it)
                        .onSuccess { token ->
                            newRequest = response.request.newBuilder().apply {
                                removeHeader("Authorization")
                                addHeader("Authorization", "Bearer ${token.accessToken}")
                            }.build()

                            if (token.refreshToken == it) {
                                tokenRepository.updateAccessToken(token.accessToken)
                            } else {
                                tokenRepository.updateTokens(
                                    token.accessToken,
                                    token.refreshToken
                                )
                            }
                        }
                        .onFailure {
                            runBlocking {
                                tokenRepository.notifyTokenUpdateFailed(true)
                            }
                        }
                }.onFailure {
                    runBlocking {
                        tokenRepository.notifyTokenUpdateFailed(true)
                    }
                }
                newRequest
            }
        } else {
            //로컬에 저장된 데이터 제거
            // RefreshToken도 만료되어 로그인화면으로 이동 .
            runBlocking {
                tokenRepository.notifyTokenUpdateFailed(true)
                tokenRepository.deleteToken()
                tokenCallback.onTokenFailure()
            }
        }

        return null
    }
}