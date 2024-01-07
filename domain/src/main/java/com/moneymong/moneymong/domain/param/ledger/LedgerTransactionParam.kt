package com.moneymong.moneymong.domain.param.ledger

data class LedgerTransactionParam(
    val id: Int,
    val storeInfo: String,
    val fundType: FundType,
    val amount: Int,
    val description: String,
    val paymentDate: String,
    val receiptImageUrls: List<String>,
    val documentImageUrls: List<String> = emptyList()
)
