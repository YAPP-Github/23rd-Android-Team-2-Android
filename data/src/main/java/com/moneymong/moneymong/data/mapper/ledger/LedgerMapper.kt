package com.moneymong.moneymong.data.mapper.ledger

import com.moneymong.moneymong.domain.entity.ledger.DocumentImageURLEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionEntity
import com.moneymong.moneymong.domain.entity.ledger.ReceiptImageURLEntity
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.network.request.ocr.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ocr.DocumentImageURL
import com.moneymong.moneymong.network.response.ocr.LedgerTransactionResponse
import com.moneymong.moneymong.network.response.ocr.ReceiptImageURL

fun LedgerTransactionParam.toRequest() =
    LedgerTransactionRequest(
        storeInfo = this.storeInfo,
        fundType = this.fundType.name,
        amount = this.amount,
        description = this.description,
        paymentDate = this.paymentDate,
        receiptImageUrls = this.receiptImageUrls,
        documentImageUrls = this.documentImageUrls
    )

fun LedgerTransactionResponse.toEntity() =
    LedgerTransactionEntity(
        id = this.id,
        storeInfo = this.storeInfo,
        amount = this.amount,
        fundType = this.fundType,
        description = this.description,
        paymentDate = this.paymentDate,
        receiptImageUrls = this.receiptImageUrls.map { it.toEntity() },
        documentImageUrls = this.documentImageUrls.map { it.toEntity() }
    )

fun ReceiptImageURL.toEntity() =
    ReceiptImageURLEntity(
        id = this.id,
        receiptImageUrl = this.receiptImageUrl
    )

fun DocumentImageURL.toEntity() =
    DocumentImageURLEntity(
        id = this.id,
        documentImageUrl = this.documentImageUrl
    )