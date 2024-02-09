package com.moneymong.moneymong.domain.entity.ledger

data class LedgerTransactionListEntity(
    val id: Int,
    val totalBalance: Int,
    val ledgerInfoViewDetails: List<LedgerDetailEntity>
)

data class LedgerDetailEntity(
    val id: Int,
    val storeInfo: String,
    val fundType: String,
    val amount: Int,
    val balance: Int,
    val order: Int,
    val paymentDate: String
)
