package com.moneymong.moneymong.network.util

import javax.inject.Inject

class TokenManager @Inject constructor(
    // 필요한 의존성 주입
) : TokenCallback {

    override suspend fun onTokenFailure() {
    }
}