package com.moneymong.moneymong.domain.entity.ocr

data class DocumentEntity(
    val version: String,
    val requestId: String,
    val timestamp: Long,
    val images: List<DocumentImageEntity>
)

data class DocumentImageEntity(
    val receipt: DocumentReceiptEntity?,
    val uid: String?,
    val name: String?,
    val inferResult: String?,
    val message: String?,
    val validationResult: ValidationResultEntity?
)

data class DocumentReceiptEntity(
    val meta: DocumentMetaEntity?,
    val result: DocumentResultEntity?
)

data class DocumentMetaEntity(
    val estimatedLanguage: String?
)

data class DocumentResultEntity(
    val storeInfo: StoreInfoEntity?,
    val paymentInfo: PaymentInfoEntity?,
    val subResults: List<SubResultEntity>?,
    val totalPrice: TotalPriceEntity?,
    val subTotal: List<SubTotalEntity>?
)
data class StoreInfoEntity(
    val name: TextInfoEntity?,
    val subName: TextInfoEntity?,
    val bizNum: TextInfoEntity?,
    val addresses: List<TextInfoEntity>?,
    val tel: List<TextInfoEntity>?
)

data class PaymentInfoEntity(
    val date: DateInfoEntity?,
    val time: TimeInfoEntity?,
    val cardInfo: CardInfoEntity?,
    val confirmNum: TextInfoEntity?
)

data class SubResultEntity(
    val items: List<SubResultItemEntity>?
)

data class SubResultItemEntity(
    val name: TextInfoEntity?,
    val code: TextInfoEntity?,
    val count: TextInfoEntity?,
    val price: PriceInfoEntity?
)

data class PriceInfoEntity(
    val price: TextInfoEntity?,
    val unitPrice: TextInfoEntity?
)

data class TotalPriceEntity(
    val price: TextInfoEntity?
)

data class SubTotalEntity(
    val taxPrice: List<TextInfoEntity>?,
    val discountPrice: List<TextInfoEntity>?
)

data class TextInfoEntity(
    val text: String?,
    val formatted: TextFormattedEntity?,
    val keyText: String?,
    val confidenceScore: Double?,
    val boundingPolys: List<VertexEntity>?
)

data class DateInfoEntity(
    val text: String?,
    val formatted: DateFormattedEntity?,
    val keyText: String?,
    val confidenceScore: Double?,
    val boundingPolys: List<VertexEntity>?
)

data class TimeInfoEntity(
    val text: String?,
    val formatted: TimeFormattedEntity?,
    val keyText: String?,
    val confidenceScore: Double?,
    val boundingPolys: List<VertexEntity>?
)

data class CardInfoEntity(
    val company: TextInfoEntity?,
    val number: TextInfoEntity?
)

data class TextFormattedEntity(
    val value: String?
)

data class DateFormattedEntity(
    val year: String?,
    val month: String?,
    val day: String?
)

data class TimeFormattedEntity(
    val hour: String?,
    val minute: String?,
    val second: String?
)

data class VertexEntity(
    val vertices: List<PointEntity>?
)

data class PointEntity(
    val x: Double?,
    val y: Double?
)

data class ValidationResultEntity(
    val result: String?
)