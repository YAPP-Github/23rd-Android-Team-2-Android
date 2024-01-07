package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.ledger.LedgerTransactionRequest
import com.moneymong.moneymong.network.request.ocr.FileUploadRequest
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionResponse
import com.moneymong.moneymong.network.response.ocr.FileUploadResponse
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

    @POST("api/v1/images")
    suspend fun postFileUpload(
        @Body body: FileUploadRequest
    ): Result<FileUploadResponse>
}