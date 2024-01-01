package com.moneymong.moneymong.network.request.ocr

data class DocumentRequest(
    val version: String = "V2", // Clova API Document에 따라 V2로 고정합니다.
    val requestId: String,
    val timestamp: Long = 0L,
    val images: List<DocumentImage>
) {
    data class DocumentImage(
        val format: String,
        val data: String,
        val name: String
    )
}