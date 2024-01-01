package com.moneymong.moneymong.data.mapper.ocr

import com.moneymong.moneymong.domain.entity.ocr.BizNumEntity
import com.moneymong.moneymong.domain.entity.ocr.CardCompanyEntity
import com.moneymong.moneymong.domain.entity.ocr.CardNumberEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentMetaEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentResultEntity
import com.moneymong.moneymong.domain.entity.ocr.PaymentCardInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.PaymentConfirmNumberEntity
import com.moneymong.moneymong.domain.entity.ocr.PaymentDateEntity
import com.moneymong.moneymong.domain.entity.ocr.PaymentInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.PaymentTimeEntity
import com.moneymong.moneymong.domain.entity.ocr.StoreAddressEntity
import com.moneymong.moneymong.domain.entity.ocr.StoreInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.StoreNameEntity
import com.moneymong.moneymong.domain.entity.ocr.StoreTelEntity
import com.moneymong.moneymong.domain.entity.ocr.SubNameEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultCountEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultItemEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultNameEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultPriceEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultPriceInfoEntity
import com.moneymong.moneymong.domain.entity.ocr.SubResultUnitPriceEntity
import com.moneymong.moneymong.domain.entity.ocr.TotalPriceEntity
import com.moneymong.moneymong.domain.entity.ocr.TotalPriceInfoEntity
import com.moneymong.moneymong.domain.param.ocr.DocumentParam
import com.moneymong.moneymong.network.request.ocr.DocumentRequest
import com.moneymong.moneymong.network.response.ocr.BizNum
import com.moneymong.moneymong.network.response.ocr.DocumentMeta
import com.moneymong.moneymong.network.response.ocr.DocumentResponse
import com.moneymong.moneymong.network.response.ocr.DocumentResult
import com.moneymong.moneymong.network.response.ocr.PaymentCardInfo
import com.moneymong.moneymong.network.response.ocr.PaymentInfo
import com.moneymong.moneymong.network.response.ocr.StoreAddress
import com.moneymong.moneymong.network.response.ocr.StoreInfo
import com.moneymong.moneymong.network.response.ocr.StoreName
import com.moneymong.moneymong.network.response.ocr.StoreTel
import com.moneymong.moneymong.network.response.ocr.SubName
import com.moneymong.moneymong.network.response.ocr.SubResult
import com.moneymong.moneymong.network.response.ocr.SubResultPriceInfo
import com.moneymong.moneymong.network.response.ocr.TotalPrice

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
        meta = this.meta.toEntity(),
        result = this.result.toEntity()
    )

fun DocumentMeta.toEntity() =
    DocumentMetaEntity(
        estimatedLanguage = this.estimatedLanguage
    )

fun DocumentResult.toEntity() =
    DocumentResultEntity(
        storeInfo = this.storeInfo.toEntity(),
        paymentInfo = this.paymentInfo.toEntity(),
        subResults = this.subResults.map { it.toEntity() },
        totalPrice = this.totalPrice.toEntity()
    )


// Store Entity Mapper
fun StoreInfo.toEntity() =
    StoreInfoEntity(
        name = this.name.toEntity(),
        subName = this.subName.toEntity(),
        bizNum = this.bizNum.toEntity(),
        address = this.address.map { it.toEntity() },
        tel = this.tel.map { it.toEntity() }
    )

fun StoreName.toEntity() =
    StoreNameEntity(
        text = this.text,
        formatted = StoreNameEntity.StoreNameEntityFormatted(this.formatted.formatted)
    )

fun SubName.toEntity() =
    SubNameEntity(
        text = this.text,
        formatted = SubNameEntity.SubNameEntityFormatted(this.formatted.formatted)
    )

fun BizNum.toEntity() =
    BizNumEntity(
        text = this.text,
        formatted = BizNumEntity.BizNumEntityFormatted(this.formatted.formatted)
    )

fun StoreAddress.toEntity() =
    StoreAddressEntity(
        text = this.text,
        formatted = StoreAddressEntity.StoreAddressEntityFormatted(this.formatted.formatted)
    )

fun StoreTel.toEntity() =
    StoreTelEntity(
        text = this.text,
        formatted = StoreTelEntity.StoreTelEntityFormatted(this.formatted.formatted)
    )

// PaymentInfo Entity Mapper
fun PaymentInfo.toEntity(): PaymentInfoEntity {
    val date = PaymentDateEntity(
        text = this.date.text,
        formatted = PaymentDateEntity.PaymentDateEntityFormatted(this.date.formatted.formatted)
    )
    val time = PaymentTimeEntity(
        text = this.time.text,
        formatted = PaymentTimeEntity.PaymentTimeEntityFormatted(this.time.formatted.formatted)
    )
    val confirmNum = PaymentConfirmNumberEntity(
        text = this.confirmNum.text,
        formatted = PaymentConfirmNumberEntity.PaymentConfirmNumberEntityFormatted(this.confirmNum.formatted.formatted)
    )
    return PaymentInfoEntity(
        date = date,
        time = time,
        cardInfo = this.cardInfo.toEntity(),
        confirmNum = confirmNum
    )
}

fun PaymentCardInfo.toEntity(): PaymentCardInfoEntity {
    val company = CardCompanyEntity(
        text = this.company.text,
        formatted = CardCompanyEntity.CardCompanyEntityFormatted(this.company.formatted.formatted)
    )
    val number = CardNumberEntity(
        text = this.number.text,
        formatted = CardNumberEntity.CardNumberEntityFormatted(this.number.formatted.formatted)
    )

    return PaymentCardInfoEntity(
        company = company,
        number = number
    )
}

// SubResult Entity Mapper
fun SubResult.toEntity() =
    SubResultEntity(
        items = this.items.map {
            SubResultItemEntity(
                name = SubResultNameEntity(
                    text = it.name.text,
                    formatted = SubResultNameEntity.SubResultNameEntityFormatted(it.name.formatted.formatted)
                ),
                count = SubResultCountEntity(
                    text = it.count.text,
                    formatted = SubResultCountEntity.SubResultCountEntityFormatted(it.count.formatted.formatted)
                ),
                price = it.price.toEntity()
            )
        }
    )

fun SubResultPriceInfo.toEntity(): SubResultPriceInfoEntity {
    val price = SubResultPriceEntity(
        text = this.price.text,
        formatted = SubResultPriceEntity.SubResultPriceEntityFormatted(this.price.formatted.formatted)
    )
    val unitPrice = SubResultUnitPriceEntity(
        text = this.unitPrice.text,
        formatted = SubResultUnitPriceEntity.SubResultUnitPriceEntityFormatted(this.unitPrice.formatted.formatted)
    )

    return SubResultPriceInfoEntity(
        price = price,
        unitPrice = unitPrice
    )
}

// TotalPrice Entity Mapper
fun TotalPrice.toEntity(): TotalPriceEntity {
    val price = TotalPriceInfoEntity(
        text = this.price.text,
        formatted = TotalPriceInfoEntity.TotalPriceInfoEntityFormatted(this.price.formatted.formatted)
    )

    return TotalPriceEntity(price = price)
}
