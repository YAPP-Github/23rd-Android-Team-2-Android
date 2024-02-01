package com.moneymong.moneymong.network.util

import com.moneymong.moneymong.domain.repository.TokenRepository
import com.moneymong.moneymong.network.BuildConfig
import kotlinx.coroutines.flow.SharedFlow
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
                //로컬에서 리프레쉬 토큰 가져오기
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
                            //it
                            //TODO 토큰 갱신 요청이 실패하거나 네트워크 문제 등으로 인해 갱신 되지 않았을 떄
                            // 에러 화면
                        }
                }
                    .onFailure {
                        //it
                        //로컬에서 리프레쉬 토큰을 가져오지 못한 경우
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