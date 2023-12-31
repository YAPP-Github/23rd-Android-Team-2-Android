package com.moneymong.moneymong.network.response.ocr

import java.sql.Timestamp

data class DocumentResponse(
    val version: String,
    val requestId: String,
    val timestamp: Timestamp,
    val meta: DocumentMeta,
    val result: DocumentResult,
)

data class DocumentMeta(
    val estimatedLanguage: String
)

data class DocumentResult(
    val storeInfo: StoreInfo,
    val paymentInfo: PaymentInfo,
    val subResults: List<SubResult>,
    val totalPrice: TotalPrice
)
data class StoreInfo(
    val name: StoreName,
    val subName: SubName,
    val bizNum: BizNum,
    val address: List<StoreAddress>,
    val tel: List<StoreTel>
)

data class StoreName(
    val text: String,
    val formatted: StoreNameFormatted,
) {
    data class StoreNameFormatted(
        val formatted: String
    )
}

data class SubName(
    val text: String,
    val formatted: SubNameFormatted
) {
    data class SubNameFormatted(
        val formatted: String
    )
}

data class BizNum(
    val text: String,
    val formatted: BizNumFormatted
) {
    data class BizNumFormatted(
        val formatted: String
    )
}

data class StoreAddress(
    val text: String,
    val formatted: StoreAddressFormatted
) {
    data class StoreAddressFormatted(
        val formatted: String
    )
}

data class StoreTel(
    val text: String,
    val formatted: StoreTelFormatted
) {
    data class StoreTelFormatted(
        val formatted: String
    )
}

data class PaymentInfo(
    val date: PaymentDate,
    val time: PaymentTime,
    val cardInfo: PaymentCardInfo,
    val confirmNum: PaymentConfirmNumber
)

data class PaymentDate(
    val text: String,
    val formatted: PaymentDateFormatted
) {
    data class PaymentDateFormatted(
        val formatted: String
    )
}

data class PaymentTime(
    val text: String,
    val formatted: PaymentTimeFormatted
) {
    data class PaymentTimeFormatted(
        val formatted: String
    )
}

data class PaymentCardInfo(
    val company: CardCompany,
    val number: CardNumber
)

data class CardCompany(
    val text: String,
    val formatted: CardCompanyFormatted
) {
    data class CardCompanyFormatted(
        val formatted: String
    )
}

data class CardNumber(
    val text: String,
    val formatted: CardNumberFormatted
) {
    data class CardNumberFormatted(
        val formatted: String
    )
}

data class PaymentConfirmNumber(
    val text: String,
    val formatted: PaymentConfirmNumberFormatted
) {
    data class PaymentConfirmNumberFormatted(
        val formatted: String
    )
}

data class SubResult(
    val items: List<SubResultItem>
)

data class SubResultItem(
    val name: SubResultName,
    val count: SubResultCount,
    val price: SubResultPriceInfo
)

data class SubResultName(
    val text: String,
    val formatted: SubResultFormatted
) {
    data class SubResultFormatted(
        val formatted: String
    )
}

data class SubResultCount(
    val text: String,
    val formatted: SubResultCountFormatted
) {
    data class SubResultCountFormatted(
        val formatted: String
    )
}

data class SubResultPriceInfo(
    val price: SubResultPrice,
    val unitPrice: SubResultUnitPrice
) {
}

data class SubResultPrice(
    val text: String,
    val formatted: SubResultPriceFormatted

) {
    data class SubResultPriceFormatted(
        val formatted: String
    )
}

data class SubResultUnitPrice(
    val text: String,
    val formatted: SubResultUnitPriceFormatted
) {
    data class SubResultUnitPriceFormatted(
        val formatted: String
    )
}

data class TotalPrice(
    val price: TotalPriceInfo
)

data class TotalPriceInfo(
    val text: String,
    val formatted: TotalPriceInfoFormatted
) {
    data class TotalPriceInfoFormatted(
        val formatted: String
    )
}