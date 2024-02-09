package com.moneymong.moneymong.network.util

interface TokenCallback {
    suspend fun onTokenFailure()
}