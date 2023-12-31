package com.moneymong.moneymong.data.datasource.ocr

import com.moneymong.moneymong.network.request.ocr.DocumentRequest
import com.moneymong.moneymong.network.response.ocr.DocumentResponse

interface OCRRemoteDataSource {
    suspend fun documentOCR(body: DocumentRequest): Result<DocumentResponse>
}