package com.moneymong.moneymong.data.datasource.ocr

import com.moneymong.moneymong.network.api.ClovaApi
import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.ocr.DocumentRequest
import com.moneymong.moneymong.network.request.ocr.FileUploadRequest
import com.moneymong.moneymong.network.response.ocr.DocumentResponse
import com.moneymong.moneymong.network.response.ocr.FileUploadResponse
import javax.inject.Inject

class OCRRemoteDataSourceImpl @Inject constructor(
    private val clovaApi: ClovaApi,
    private val moneyMongApi: MoneyMongApi
): OCRRemoteDataSource {
    override suspend fun documentOCR(body: DocumentRequest): Result<DocumentResponse> =
        clovaApi.documentOCR(body = body)

    override suspend fun postFileUpload(body: FileUploadRequest): Result<FileUploadResponse> =
        moneyMongApi.postFileUpload(file = body.file)
}