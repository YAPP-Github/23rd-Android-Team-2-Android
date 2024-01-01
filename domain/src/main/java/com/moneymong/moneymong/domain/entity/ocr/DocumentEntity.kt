package com.moneymong.moneymong.domain.entity.ocr

data class DocumentEntity(
    val version: String,
    val requestId: String,
    val timestamp: Long,
    val meta: DocumentMetaEntity,
    val result: DocumentResultEntity,
)

data class DocumentMetaEntity(
    val estimatedLanguage: String
)

data class DocumentResultEntity(
    val storeInfo: StoreInfoEntity,
    val paymentInfo: PaymentInfoEntity,
    val subResults: List<SubResultEntity>,
    val totalPrice: TotalPriceEntity
)
data class StoreInfoEntity(
    val name: StoreNameEntity,
    val subName: SubNameEntity,
    val bizNum: BizNumEntity,
    val address: List<StoreAddressEntity>,
    val tel: List<StoreTelEntity>
)

data class StoreNameEntity(
    val text: String,
    val formatted: StoreNameEntityFormatted,
) {
    data class StoreNameEntityFormatted(
        val formatted: String
    )
}

data class SubNameEntity(
    val text: String,
    val formatted: SubNameEntityFormatted
) {
    data class SubNameEntityFormatted(
        val formatted: String
    )
}

data class BizNumEntity(
    val text: String,
    val formatted: BizNumEntityFormatted
) {
    data class BizNumEntityFormatted(
        val formatted: String
    )
}

data class StoreAddressEntity(
    val text: String,
    val formatted: StoreAddressEntityFormatted
) {
    data class StoreAddressEntityFormatted(
        val formatted: String
    )
}

data class StoreTelEntity(
    val text: String,
    val formatted: StoreTelEntityFormatted
) {
    data class StoreTelEntityFormatted(
        val formatted: String
    )
}

data class PaymentInfoEntity(
    val date: PaymentDateEntity,
    val time: PaymentTimeEntity,
    val cardInfo: PaymentCardInfoEntity,
    val confirmNum: PaymentConfirmNumberEntity
)

data class PaymentDateEntity(
    val text: String,
    val formatted: PaymentDateEntityFormatted
) {
    data class PaymentDateEntityFormatted(
        val formatted: String
    )
}

data class PaymentTimeEntity(
    val text: String,
    val formatted: PaymentTimeEntityFormatted
) {
    data class PaymentTimeEntityFormatted(
        val formatted: String
    )
}

data class PaymentCardInfoEntity(
    val company: CardCompanyEntity,
    val number: CardNumberEntity
)

data class CardCompanyEntity(
    val text: String,
    val formatted: CardCompanyEntityFormatted
) {
    data class CardCompanyEntityFormatted(
        val formatted: String
    )
}

data class CardNumberEntity(
    val text: String,
    val formatted: CardNumberEntityFormatted
) {
    data class CardNumberEntityFormatted(
        val formatted: String
    )
}

data class PaymentConfirmNumberEntity(
    val text: String,
    val formatted: PaymentConfirmNumberEntityFormatted
) {
    data class PaymentConfirmNumberEntityFormatted(
        val formatted: String
    )
}

data class SubResultEntity(
    val items: List<SubResultItemEntity>
)

data class SubResultItemEntity(
    val name: SubResultNameEntity,
    val count: SubResultCountEntity,
    val price: SubResultPriceInfoEntity
)

data class SubResultNameEntity(
    val text: String,
    val formatted: SubResultNameEntityFormatted
) {
    data class SubResultNameEntityFormatted(
        val formatted: String
    )
}

data class SubResultCountEntity(
    val text: String,
    val formatted: SubResultCountEntityFormatted
) {
    data class SubResultCountEntityFormatted(
        val formatted: String
    )
}

data class SubResultPriceInfoEntity(
    val price: SubResultPriceEntity,
    val unitPrice: SubResultUnitPriceEntity
) {
}

data class SubResultPriceEntity(
    val text: String,
    val formatted: SubResultPriceEntityFormatted

) {
    data class SubResultPriceEntityFormatted(
        val formatted: String
    )
}

data class SubResultUnitPriceEntity(
    val text: String,
    val formatted: SubResultUnitPriceEntityFormatted
) {
    data class SubResultUnitPriceEntityFormatted(
        val formatted: String
    )
}

data class TotalPriceEntity(
    val price: TotalPriceInfoEntity
)

data class TotalPriceInfoEntity(
    val text: String,
    val formatted: TotalPriceInfoEntityFormatted
) {
    data class TotalPriceInfoEntityFormatted(
        val formatted: String
    )
}