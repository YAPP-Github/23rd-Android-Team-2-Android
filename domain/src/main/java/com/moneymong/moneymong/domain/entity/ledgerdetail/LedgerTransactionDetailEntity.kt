package com.moneymong.moneymong.domain.entity.ledgerdetail

import com.moneymong.moneymong.domain.entity.ledger.DocumentImageURLEntity
import com.moneymong.moneymong.domain.entity.ledger.ReceiptImageURLEntity

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
