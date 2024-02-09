package com.moneymong.moneymong.data.mapper.ledgerdetail

import com.moneymong.moneymong.data.mapper.ledger.toEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerDetailEntity
import com.moneymong.moneymong.domain.entity.ledgerdetail.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerDocumentRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerReceiptRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerTransactionDetailRequest
import com.moneymong.moneymong.network.response.ledger.LedgerDetail
import com.moneymong.moneymong.network.response.ledgerdetail.LedgerTransactionDetailResponse

fun LedgerReceiptParam.toRequest() =
    LedgerReceiptRequest(
        receiptImageUrls = this.receiptImageUrls
    )

fun LedgerDocumentParam.toRequest() =
    LedgerDocumentRequest(
        documentImageUrls = this.documentImageUrls
    )

fun LedgerTransactionDetailParam.toRequest() =
    LedgerTransactionDetailRequest(
        storeInfo = this.storeInfo,
        amount = this.amount,
        description = this.description,
        paymentDate = this.paymentDate
    )

fun LedgerDetail.toEntity() =
    LedgerDetailEntity(
        id = this.id,
        storeInfo = this.storeInfo,
        fundType = this.fundType,
        amount = this.amount,
        balance = this.balance,
        order = this.order,
        paymentDate = this.paymentDate
    )

fun LedgerTransactionDetailResponse.toEntity() =
    LedgerTransactionDetailEntity(
        id = this.id,
        storeInfo = this.storeInfo,
        amount = this.amount,
        fundType = this.fundType,
        description = this.description,
        paymentDate = this.paymentDate,
        receiptImageUrls = this.receiptImageUrls.map { it.toEntity() },
        documentImageUrls = this.documentImageUrls.map { it.toEntity() },
        authorName = this.authorName
    )