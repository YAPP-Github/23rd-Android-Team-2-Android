package com.moneymong.moneymong.network.response.ledgerdetail

import com.moneymong.moneymong.network.response.ledger.DocumentImageURL
import com.moneymong.moneymong.network.response.ledger.ReceiptImageURL

data class LedgerTransactionDetailResponse(
    val id: Int,
    val storeInfo: String,
    val amount: Int,
    val fundType: String,
    val description: String,
    val paymentDate: String,
    val receiptImageUrls: List<ReceiptImageURL>,
    val documentImageUrls: List<DocumentImageURL>,
    val authorName: String
)