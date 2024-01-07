package com.moneymong.moneymong.network.util

import com.moneymong.moneymong.domain.repository.LoginRepository
import com.moneymong.moneymong.domain.repository.TokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class MoneyMongTokenAuthenticator @Inject constructor(
    private val tokenRepository: TokenRepository
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401) {
            runBlocking {
                val refreshToken = tokenRepository.getRefreshToken()
                tokenRepository.getUpdateToken(refreshToken)
                    .onSuccess {
                        response.request.newBuilder().apply {
                            removeHeader("Authorization")
                            addHeader("Authorization", "Bearer ${it.accessToken}")
                        }.build()
                        //refreshToken이 만료되지 않은 경우
                        if (it.refreshToken == "") {
                            tokenRepository.updateAccessToken(it.accessToken)
                        }
                        //refreshToken이 만료된 경우
                        else {
                            tokenRepository.updateTokens(it.accessToken, it.refreshToken)
                        }
                    }
                    .onFailure {
                        //TODO
                    }

            }
        } else {
            // TODO Move To Login Screen Logic
            // RefreshToken도 만료되어 로그인이 다시 필요한 상황입니다.
            // ex. 로그인 화면으로 이동
            null
        }

        // token refresh도 401 일 경우 무한루프에 빠지는 것을 막기 위해 null return
        return null
    }
}

