package com.moneymong.moneymong.network.util

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class MoneyMongTokenAuthenticator @Inject constructor(): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401) {
            response.request.newBuilder().apply {
                // TODO token refresh verification logic
                removeHeader("Authorization")
                addHeader("Authorization", "token") // TODO
            }.build()
        } else {
            // TODO Move To Login Screen Logic
            null
        }

        // token refresh도 401 일 경우 무한루프에 빠지는 것을 막기 위해 null return
        return null
    }
}