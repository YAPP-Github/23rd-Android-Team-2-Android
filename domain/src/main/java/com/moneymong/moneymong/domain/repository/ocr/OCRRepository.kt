package com.moneymong.moneymong.domain.repository.ocr

import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.param.ocr.DocumentParam

interface OCRRepository {
    suspend fun documentOCR(body: DocumentParam): Result<DocumentEntity>
}