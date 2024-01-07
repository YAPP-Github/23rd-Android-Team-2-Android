package com.moneymong.moneymong.data.datasource.ledger

import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.ocr.LedgerTransactionRequest
import javax.inject.Inject

class LedgerRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
): LedgerRemoteDataSource {
    override suspend fun postLedgerTransaction(id: Int, body: LedgerTransactionRequest) =
        moneyMongApi.postLedgerTransaction(id, body)
}