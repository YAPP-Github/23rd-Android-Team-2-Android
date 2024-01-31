package com.moneymong.moneymong.network.util

import com.moneymong.moneymong.domain.repository.LoginRepository
import com.moneymong.moneymong.domain.repository.TokenRepository
import com.moneymong.moneymong.network.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // refreshTokenApi 호출
        if (originalRequest.url.toString() == BuildConfig.MONEYMONG_BASE_URL + "api/v1/tokens"
            || originalRequest.url.toString() == BuildConfig.MONEYMONG_BASE_URL + "api/v1/users"
        ) {
            return chain.proceed(originalRequest)
        }

        val accessToken = runBlocking {
            tokenRepository.getAccessToken()
        }

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${accessToken.getOrNull()}")
            .build()

        return chain.proceed(newRequest)
    }
}
