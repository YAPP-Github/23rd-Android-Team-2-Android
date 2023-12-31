package com.moneymong.moneymong.data.datasource.ocr

import com.moneymong.moneymong.network.api.ClovaApi
import com.moneymong.moneymong.network.request.ocr.DocumentRequest
import com.moneymong.moneymong.network.response.ocr.DocumentResponse
import javax.inject.Inject

class OCRRemoteDataSourceImpl @Inject constructor(
    private val clovaApi: ClovaApi
): OCRRemoteDataSource {
    override suspend fun documentOCR(body: DocumentRequest): Result<DocumentResponse> =
        clovaApi.documentOCR(body = body)
}