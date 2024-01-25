package com.moneymong.moneymong.network.request.ledger

data class LedgerTransactionDetailRequest(
    val storeInfo: String,
    val amount: Int,
    val description: String,
    val paymentDate: String
)