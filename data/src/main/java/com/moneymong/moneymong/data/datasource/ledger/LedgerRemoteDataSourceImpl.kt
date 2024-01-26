package com.moneymong.moneymong.data.datasource.ledger

import com.moneymong.moneymong.network.api.LedgerApi
import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.ledger.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionListResponse
import javax.inject.Inject

class LedgerRemoteDataSourceImpl @Inject constructor(
    private val ledgerApi: LedgerApi
): LedgerRemoteDataSource {
    override suspend fun fetchLedgerTransactionList(
        id: Int,
        year: Int,
        month: Int,
        page: Int,
        limit: Int
    ): Result<LedgerTransactionListResponse> =
        ledgerApi.fetchLedgerTransactionList(
            id = id,
            year = year,
            month = month,
            page = page,
            limit = limit
        )

    override suspend fun fetchAgencyExistLedger(agencyId: Int): Result<Boolean> =
        ledgerApi.fetchAgencyExistLedger(agencyId = agencyId)

    override suspend fun postLedgerTransaction(id: Int, body: LedgerTransactionRequest) =
        ledgerApi.postLedgerTransaction(id = id, body = body)
}