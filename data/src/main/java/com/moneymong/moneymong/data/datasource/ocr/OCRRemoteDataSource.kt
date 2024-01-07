package com.moneymong.moneymong.data.datasource.ocr

import com.moneymong.moneymong.network.request.ocr.DocumentRequest
import com.moneymong.moneymong.network.request.ocr.FileUploadRequest
import com.moneymong.moneymong.network.response.ocr.DocumentResponse
import com.moneymong.moneymong.network.response.ocr.FileUploadResponse

interface OCRRemoteDataSource {
    suspend fun documentOCR(body: DocumentRequest): Result<DocumentResponse>
    suspend fun postFileUpload(body: FileUploadRequest): Result<FileUploadResponse>
}