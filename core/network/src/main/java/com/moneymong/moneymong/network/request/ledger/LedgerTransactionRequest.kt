package com.moneymong.moneymong.network.request.ledger

data class LedgerTransactionRequest(
    val storeInfo: String,
    val fundType: String,
    val amount: Int,
    val description: String,
    val paymentDate: String,
    val receiptImageUrls: List<String>,
    val documentImageUrls: List<String> = emptyList()
)
