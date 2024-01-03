package com.moneymong.moneymong.network.response.ocr


data class DocumentResponse(
    val version: String,
    val requestId: String,
    val timestamp: Long,
    val images: List<DocumentImage>
)

data class DocumentImage(
    val receipt: DocumentReceipt?,
    val uid: String?,
    val name: String?,
    val inferResult: String?,
    val message: String?,
    val validationResult: ValidationResult?
)

data class DocumentReceipt(
    val meta: DocumentMeta? = null,
    val result: DocumentResult? = null
)

data class DocumentMeta(
    val estimatedLanguage: String? = null
)

data class DocumentResult(
    val storeInfo: StoreInfo? = null,
    val paymentInfo: PaymentInfo? = null,
    val subResults: List<SubResult>? = null,
    val totalPrice: TotalPrice? = null,
    val subTotal: List<SubTotal>? = null
)
data class StoreInfo(
    val name: TextInfo? = null,
    val subName: TextInfo? = null,
    val bizNum: TextInfo? = null,
    val addresses: List<TextInfo>? = null,
    val tel: List<TextInfo>? = null
)

data class PaymentInfo(
    val date: DateInfo? = null,
    val time: TimeInfo? = null,
    val cardInfo: CardInfo? = null,
    val confirmNum: TextInfo? = null
)

data class SubResult(
    val items: List<SubResultItem>? = null
)

data class SubResultItem(
    val name: TextInfo? = null,
    val code: TextInfo? = null,
    val count: TextInfo? = null,
    val price: PriceInfo? = null
)

data class PriceInfo(
    val price: TextInfo? = null,
    val unitPrice: TextInfo? = null
)

data class TotalPrice(
    val price: TextInfo? = null
)

data class SubTotal(
    val taxPrice: List<TextInfo>? = null,
    val discountPrice: List<TextInfo>?  = null
)

data class TextInfo(
    val text: String? = null,
    val formatted: TextFormatted? = null,
    val keyText: String? = null,
    val confidenceScore: Double? = null,
    val boundingPolys: List<Vertex>? = null
)

data class DateInfo(
    val text: String? = null,
    val formatted: DateFormatted? = null,
    val keyText: String? = null,
    val confidenceScore: Double? = null,
    val boundingPolys: List<Vertex>?  = null
)

data class TimeInfo(
    val text: String? = null,
    val formatted: TimeFormatted? = null,
    val keyText: String? = null,
    val confidenceScore: Double? = null,
    val boundingPolys: List<Vertex>? = null
)

data class CardInfo(
    val company: TextInfo? = null,
    val number: TextInfo? = null
)

data class TextFormatted(
    val value: String? = null
)

data class DateFormatted(
    val year: String? = null,
    val month: String? = null,
    val day: String? = null
)

data class TimeFormatted(
    val hour: String? = null,
    val minute: String? = null,
    val second: String? = null
)

data class Vertex(
    val vertices: List<Point>? = null
)

data class Point(
    val x: Double? = null,
    val y: Double? = null
)

data class ValidationResult(
    val result: String? = null
)