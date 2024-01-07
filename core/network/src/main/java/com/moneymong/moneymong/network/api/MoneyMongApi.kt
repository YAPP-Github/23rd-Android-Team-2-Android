package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.ocr.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ocr.LedgerTransactionResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface MoneyMongApi {

    // POST
    @POST("api/v1/ledger/{id}")
    suspend fun postLedgerTransaction(
        @Path("id") id: Int,
        @Body body: LedgerTransactionRequest
    ): Result<LedgerTransactionResponse>
}