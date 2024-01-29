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
            runBlocking {
                //로컬에서 리프레쉬 토큰 가져오기
                tokenRepository.getRefreshToken().onSuccess {
                    //액세스 토큰 갱신 요청
                    tokenRepository.getUpdateToken(it)
                        .onSuccess { token ->
                            response.request.newBuilder().apply {
                                removeHeader("Authorization")
                                addHeader("Authorization", "Bearer ${token.accessToken}")
                            }.build()
                            //refreshToken이 만료되지 않은 경우
                            if (token.refreshToken == it) {
                                tokenRepository.updateAccessToken(token.accessToken)
                            }
                            //refreshToken의 만료일이 1주일 이내인 경우
                            else {
                                tokenRepository.updateTokens(
                                    token.accessToken,
                                    token.refreshToken!!
                                )
                            }
                        }
                        .onFailure {
                            //TODO 토큰 갱신 요청이 실패하거나 네트워크 문제 등으로 인해 갱신 되지 않았을 떄
                            // 에러 화면
                        }
                }
                    .onFailure {
                        //로컬에서 리프레쉬 토큰을 가져오지 못한 경우
                    }
            }
        } else {
            //로컬에 저장된 데이터 제거
            // RefreshToken도 만료되어 로그인화면으로 이동 .
            runBlocking {
                tokenRepository.deleteToken()
                tokenCallback.onTokenFailure()
            }
            null
        }

        // token refresh도 401 일 경우 무한루프에 빠지는 것을 막기 위해 null return
        return null
    }
}

