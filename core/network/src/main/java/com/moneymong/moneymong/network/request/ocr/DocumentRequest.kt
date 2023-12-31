package com.moneymong.moneymong.network.request.ocr

enum class DocumentFormat(val format: String) {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    PDF("pdf"),
    TIFF("tiff")
}

data class DocumentRequest(
    val version: String = "V2", // Clova API Document에 따라 V2로 고정합니다.
    val requestId: String,
    val timestamp: Long = 0L,
    val images: List<DocumentImage>
) {
    data class DocumentImage(
        val format: String = DocumentFormat.JPEG.format,
        val data: String,
        val name: String
    )
}