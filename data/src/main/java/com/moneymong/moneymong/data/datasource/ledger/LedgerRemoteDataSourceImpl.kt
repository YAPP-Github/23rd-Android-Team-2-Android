package com.moneymong.moneymong.data.datasource.ledger

import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.ledger.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionDetailResponse
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionListResponse
import javax.inject.Inject

class LedgerRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
): LedgerRemoteDataSource {
    override suspend fun fetchLedgerTransactionList(
        id: Int,
        year: Int,
        month: Int,
        page: Int,
        limit: Int
    ): Result<LedgerTransactionListResponse> =
        moneyMongApi.fetchLedgerTransactionList(
            id = id,
            year = year,
            month = month,
            page = page,
            limit = limit
        )

    override suspend fun fetchAgencyExistLedger(agencyId: Int): Result<Boolean> =
        moneyMongApi.fetchAgencyExistLedger(agencyId = agencyId)

    override suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailResponse> =
        moneyMongApi.fetchLedgerTransactionDetail(detailId = detailId)

    override suspend fun postLedgerTransaction(id: Int, body: LedgerTransactionRequest) =
        moneyMongApi.postLedgerTransaction(id = id, body = body)
}