package com.moneymong.moneymong.data.mapper.ledgerdetail

import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerDocumentRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerReceiptRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerTransactionDetailRequest

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