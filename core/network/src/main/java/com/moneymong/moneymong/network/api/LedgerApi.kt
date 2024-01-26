package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.ledger.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionListResponse
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LedgerApi {

    // GET
    @GET("api/v1/ledger/{id}")
    suspend fun fetchLedgerTransactionList(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Path("id") id: Int,
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Result<LedgerTransactionListResponse>

    @GET("api/v1/ledger/agencies/{agencyId}/exist")
    suspend fun fetchAgencyExistLedger(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Path("agencyId") agencyId: Int
    ): Result<Boolean>

    // POST
    @POST("api/v1/ledger/{id}")
    suspend fun postLedgerTransaction(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Path("id") id: Int,
        @Body body: LedgerTransactionRequest
    ): Result<LedgerTransactionResponse>
}