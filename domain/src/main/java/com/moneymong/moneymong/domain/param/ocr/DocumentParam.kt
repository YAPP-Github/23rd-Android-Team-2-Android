package com.moneymong.moneymong.domain.param.ocr

enum class DocumentFormat(val format: String) {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    PDF("pdf"),
    TIFF("tiff")
}

data class DocumentParam(
    val version: String = "V2",
    val requestId: String,
    val timestamp: Long,
    val images: List<DocumentImageParam>
) {
    data class DocumentImageParam(
        val format: String = DocumentFormat.JPEG.format,
        val data: String,
        val name: String
    )
}