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
            response.request.url.toString() == BuildConfig.BASE_URL + "api/v1/tokens"

        if (response.code == 401 && !isPathRefresh) {
            runBlocking {
                val refreshToken = tokenRepository.getRefreshToken()
                tokenRepository.getUpdateToken(refreshToken)
                    .onSuccess {
                        response.request.newBuilder().apply {
                            removeHeader("Authorization")
                            addHeader("Authorization", "Bearer ${it.accessToken}")
                        }.build()
                        //refreshToken이 만료되지 않은 경우
                        if (it.refreshToken == null) {
                            tokenRepository.updateAccessToken(it.accessToken)
                        }
                        //refreshToken의 만료일이 1주일 이내인 경우
                        else {
                            tokenRepository.updateTokens(it.accessToken, it.refreshToken!!)
                        }
                    }
                    .onFailure {
                        //TODO 토큰 갱신 요청이 실패하거나 네트워크 문제 등으로 인해 갱신 되지 않았을 떄
                        // 에러 화면
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

