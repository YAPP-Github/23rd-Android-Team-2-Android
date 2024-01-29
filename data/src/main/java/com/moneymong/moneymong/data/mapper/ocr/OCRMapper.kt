package com.moneymong.moneymong.data.mapper.ocr

import com.moneymong.moneymong.common.ext.toMultipart
import com.moneymong.moneymong.domain.entity.ocr.CardInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.DateFormattedEntity
import com.moneymong.moneymong.domain.entity.ocr.DateInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentImageEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentMetaEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentReceiptEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentResultEntity
import com.moneymong.moneymong.domain.entity.ocr.FileUploadEntity
import com.moneymong.moneymong.domain.entity.ocr.PaymentInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.PointEntity
import com.moneymong.moneymong.domain.entity.ocr.PriceInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.StoreInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultItemEntity
import com.moneymong.moneymong.domain.entity.ocr.SubTotalEntity
import com.moneymong.moneymong.domain.entity.ocr.TextFormattedEntity
import com.moneymong.moneymong.domain.entity.ocr.TextInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.TimeFormattedEntity
import com.moneymong.moneymong.domain.entity.ocr.TimeInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.TotalPriceEntity
import com.moneymong.moneymong.domain.entity.ocr.ValidationResultEntity
import com.moneymong.moneymong.domain.entity.ocr.VertexEntity
import com.moneymong.moneymong.domain.param.ocr.DocumentParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.network.request.ocr.DocumentRequest
import com.moneymong.moneymong.network.request.ocr.FileUploadRequest
import com.moneymong.moneymong.network.response.ocr.CardInfo
import com.moneymong.moneymong.network.response.ocr.DateFormatted
import com.moneymong.moneymong.network.response.ocr.DateInfo
import com.moneymong.moneymong.network.response.ocr.DocumentImage
import com.moneymong.moneymong.network.response.ocr.DocumentMeta
import com.moneymong.moneymong.network.response.ocr.DocumentReceipt
import com.moneymong.moneymong.network.response.ocr.DocumentResponse
import com.moneymong.moneymong.network.response.ocr.DocumentResult
import com.moneymong.moneymong.network.response.ocr.FileUploadResponse
import com.moneymong.moneymong.network.response.ocr.PaymentInfo
import com.moneymong.moneymong.network.response.ocr.Point
import com.moneymong.moneymong.network.response.ocr.PriceInfo
import com.moneymong.moneymong.network.response.ocr.StoreInfo
import com.moneymong.moneymong.network.response.ocr.SubResult
import com.moneymong.moneymong.network.response.ocr.SubResultItem
import com.moneymong.moneymong.network.response.ocr.SubTotal
import com.moneymong.moneymong.network.response.ocr.TextFormatted
import com.moneymong.moneymong.network.response.ocr.TextInfo
import com.moneymong.moneymong.network.response.ocr.TimeFormatted
import com.moneymong.moneymong.network.response.ocr.TimeInfo
import com.moneymong.moneymong.network.response.ocr.TotalPrice
import com.moneymong.moneymong.network.response.ocr.ValidationResult
import com.moneymong.moneymong.network.response.ocr.Vertex

fun DocumentParam.toRequest() =
    DocumentRequest(
        version = this.version,
        requestId = this.requestId,
        timestamp = this.timestamp,
        images = this.images.map { it.toRequest() }
    )

fun DocumentParam.DocumentImageParam.toRequest() =
    DocumentRequest.DocumentImage(
        format = this.format,
        data = this.data,
        name = this.name
    )

fun DocumentResponse.toEntity() =
    DocumentEntity(
        version = this.version,
        requestId = this.requestId,
        timestamp = this.timestamp,
        images = this.images.map { it.toEntity() }
    )

fun DocumentImage.toEntity() =
    DocumentImageEntity(
        receipt = this.receipt?.toEntity(),
        uid = this.uid,
        name = this.name,
        inferResult = this.inferResult,
        message = this.message,
        validationResult = this.validationResult?.toEntity()
    )

fun DocumentReceipt.toEntity() =
    DocumentReceiptEntity(
        meta = this.meta?.toEntity(),
        result = this.result?.toEntity()
    )

fun DocumentMeta.toEntity() =
    DocumentMetaEntity(
        estimatedLanguage = this.estimatedLanguage
    )

fun DocumentResult.toEntity() =
    DocumentResultEntity(
        storeInfo = this.storeInfo?.toEntity(),
        paymentInfo = this.paymentInfo?.toEntity(),
        subResults = this.subResults?.map { it.toEntity() },
        totalPrice = this.totalPrice?.toEntity(),
        subTotal = this.subTotal?.map { it.toEntity() }
    )

fun StoreInfo.toEntity() =
    StoreInfoEntity(
        name = this.name?.toEntity(),
        subName = this.subName?.toEntity(),
        bizNum = this.bizNum?.toEntity(),
        addresses = this.addresses?.map { it.toEntity() },
        tel = this.tel?.map { it.toEntity() }
    )

fun PaymentInfo.toEntity() =
    PaymentInfoEntity(
        date = this.date?.toEntity(),
        time = this.time?.toEntity(),
        cardInfo = this.cardInfo?.toEntity(),
        confirmNum = this.confirmNum?.toEntity()
    )

fun SubResult.toEntity() =
    SubResultEntity(
        items = this.items?.map { it.toEntity() }
    )

fun SubResultItem.toEntity() =
    SubResultItemEntity(
        name = this.name?.toEntity(),
        code = this.code?.toEntity(),
        count = this.count?.toEntity(),
        price = this.price?.toEntity()
    )

fun PriceInfo.toEntity() =
    PriceInfoEntity(
        price = this.price?.toEntity(),
        unitPrice = this.unitPrice?.toEntity()
    )

fun TotalPrice.toEntity() =
    TotalPriceEntity(
        price = this.price?.toEntity()
    )

fun SubTotal.toEntity() =
    SubTotalEntity(
        taxPrice = this.taxPrice?.map { it.toEntity() },
        discountPrice = this.discountPrice?.map { it.toEntity() }
    )

fun TextInfo.toEntity() =
    TextInfoEntity(
        text = this.text,
        formatted = this.formatted?.toEntity(),
        keyText = this.keyText,
        confidenceScore = this.confidenceScore,
        boundingPolys = this.boundingPolys?.map { it.toEntity() }
    )

fun DateInfo.toEntity() =
    DateInfoEntity(
        text = this.text,
        formatted = this.formatted?.toEntity(),
        keyText = this.keyText,
        confidenceScore = this.confidenceScore,
        boundingPolys = this.boundingPolys?.map { it.toEntity() }
    )

fun TimeInfo.toEntity() =
    TimeInfoEntity(
        text = this.text,
        formatted = this.formatted?.toEntity(),
        keyText = this.keyText,
        confidenceScore = this.confidenceScore,
        boundingPolys = this.boundingPolys?.map { it.toEntity() }
    )

fun CardInfo.toEntity() =
    CardInfoEntity(
        company = this.company?.toEntity(),
        number = this.number?.toEntity()
    )

fun TextFormatted.toEntity() =
    TextFormattedEntity(
        value = this.value
    )

fun DateFormatted.toEntity() =
    DateFormattedEntity(
        year = this.year,
        month = this.month,
        day = this.day
    )

fun TimeFormatted.toEntity() =
    TimeFormattedEntity(
        hour = this.hour,
        minute = this.minute,
        second = this.second
    )

fun Vertex.toEntity() =
    VertexEntity(
        vertices = this.vertices?.map { it.toEntity() }
    )

fun Point.toEntity() =
    PointEntity(
        x = this.x,
        y = this.y
    )

fun ValidationResult.toEntity() =
    ValidationResultEntity(
        result = this.result
    )

fun FileUploadParam.toRequest() =
    FileUploadRequest(
        file = this.file.toMultipart(),
        dirName = this.dirName
    )

fun FileUploadResponse.toEntity() =
    FileUploadEntity(
        key = this.key,
        path = this.path
    )