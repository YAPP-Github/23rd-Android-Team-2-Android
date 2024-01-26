package com.moneymong.moneymong.data.repository.ocr

import com.moneymong.moneymong.data.datasource.ocr.OCRRemoteDataSource
import com.moneymong.moneymong.data.mapper.ocr.toEntity
import com.moneymong.moneymong.data.mapper.ocr.toRequest
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.entity.ocr.FileUploadEntity
import com.moneymong.moneymong.domain.param.ocr.DocumentParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.repository.ocr.OCRRepository
import javax.inject.Inject

class OCRRepositoryImpl @Inject constructor(
    private val ocrRemoteDataSource: OCRRemoteDataSource
): OCRRepository {
    override suspend fun documentOCR(body: DocumentParam): Result<DocumentEntity> =
        ocrRemoteDataSource.documentOCR(body.toRequest()).map { it.toEntity() }

    override suspend fun postFileUpload(body: FileUploadParam): Result<FileUploadEntity> =
        ocrRemoteDataSource.postFileUpload(body.toRequest()).map { it.toEntity() }
}