package com.moneymong.moneymong.network.response.ledger

data class LedgerTransactionListResponse(
    val id: Int,
    val totalBalance: Int,
    val ledgerInfoViewDetails: List<LedgerDetail>
)

data class LedgerDetail(
    val id: Int,
    val storeInfo: String,
    val fundType: String,
    val amount: Int,
    val balance: Int,
    val order: Int,
    val paymentDate: String
)