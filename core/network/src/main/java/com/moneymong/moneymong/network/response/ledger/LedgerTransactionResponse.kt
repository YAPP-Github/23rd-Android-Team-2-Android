package com.moneymong.moneymong.network.response.ledger

data class LedgerTransactionResponse(
    val id: Int,
    val storeInfo: String,
    val amount: Int,
    val fundType: String,
    val description: String,
    val paymentDate: String,
    val receiptImageUrls: List<ReceiptImageURL>,
    val documentImageUrls: List<DocumentImageURL>
)

data class ReceiptImageURL(
    val id: Int,
    val receiptImageUrl: String
)


data class DocumentImageURL(
    val id: Int,
    val documentImageUrl: String
)