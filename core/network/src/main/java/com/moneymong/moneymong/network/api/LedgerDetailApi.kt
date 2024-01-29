package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.ledgerdetail.LedgerDocumentRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerReceiptRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerTransactionDetailRequest
import com.moneymong.moneymong.network.response.ledgerdetail.LedgerTransactionDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LedgerDetailApi {

    // GET
    @GET("api/v1/ledger-detail/{detailId}")
    suspend fun fetchLedgerTransactionDetail(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Path("detailId") detailId: Int
    ): Result<LedgerTransactionDetailResponse>

    // POST
    @POST("api/v1/ledger-detail/{detailId}/ledger-receipt")
    suspend fun postLedgerReceiptTransaction(
        @Path("detailId") detailId: Int,
        @Body body: LedgerReceiptRequest
    ): Result<Unit>

    @POST("api/v1/ledger-detail/{detailId}/ledger-document")
    suspend fun postLedgerDocumentTransaction(
        @Path("detailId") detailId: Int,
        @Body body: LedgerDocumentRequest
    ): Result<Unit>

    // PUT
    @PUT("api/v1/ledger/ledger-detail/{detailId}")
    suspend fun updateLedgerTransactionDetail(
        @Path("detailId") detailId: Int,
        @Body body: LedgerTransactionDetailRequest
    ): Result<LedgerTransactionDetailResponse>

    // DELETE
    @DELETE("api/v1/ledger-detail/{detailId}/ledger-receipt/{receiptId}")
    suspend fun deleteLedgerReceiptTransaction(
        @Path("detailId") detailId: Int,
        @Path("receiptId") receiptId: Int
    ): Result<Unit>

    @DELETE("api/v1/ledger-detail/{detailId}/ledger-document/{documentId}")
    suspend fun deleteLedgerDocumentTransaction(
        @Path("detailId") detailId: Int,
        @Path("documentId") documentId: Int
    ): Result<Unit>

    @DELETE("api/v1/ledger-detail/{detailId}")
    suspend fun deleteLedgerDetail(
        @Path("detailId") detailId: Int
    ): Result<Unit>
}