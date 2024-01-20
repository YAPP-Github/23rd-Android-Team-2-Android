package com.moneymong.moneymong.domain.entity.ledger

data class LedgerTransactionDetailEntity(
    val id: Int,
    val storeInfo: String,
    val amount: Int,
    val fundType: String,
    val description: String,
    val paymentDate: String,
    val receiptImageUrls: List<ReceiptImageURLEntity>,
    val documentImageUrls: List<DocumentImageURLEntity>,
    val authorName: String
)
