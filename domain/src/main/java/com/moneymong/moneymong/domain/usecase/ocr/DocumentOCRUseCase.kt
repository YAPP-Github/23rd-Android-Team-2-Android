package com.moneymong.moneymong.domain.usecase.ocr

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.param.ocr.DocumentParam
import com.moneymong.moneymong.domain.repository.ocr.OCRRepository
import javax.inject.Inject

class DocumentOCRUseCase @Inject constructor(
    private val ocrRepository: OCRRepository
): BaseUseCase<DocumentParam, Result<DocumentEntity>>() {
    override suspend fun invoke(data: DocumentParam): Result<DocumentEntity> =
        ocrRepository.documentOCR(data)
}