package com.moneymong.moneymong.domain.repository.ocr

import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.entity.ocr.FileUploadEntity
import com.moneymong.moneymong.domain.param.ocr.DocumentParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam

interface OCRRepository {
    suspend fun documentOCR(body: DocumentParam): Result<DocumentEntity>
    suspend fun postFileUpload(body: FileUploadParam): Result<FileUploadEntity>
}