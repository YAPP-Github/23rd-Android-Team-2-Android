package com.moneymong.moneymong.domain.entity.ledger

data class LedgerTransactionEntity(
    val id: Int,
    val storeInfo: String,
    val amount: Int,
    val fundType: String,
    val description: String,
    val paymentDate: String,
    val receiptImageUrls: List<ReceiptImageURLEntity>,
    val documentImageUrls: List<DocumentImageURLEntity>
)

data class ReceiptImageURLEntity(
    val id: Int,
    val receiptImageUrl: String
)


data class DocumentImageURLEntity(
    val id: Int,
    val documentImageUrl: String
)