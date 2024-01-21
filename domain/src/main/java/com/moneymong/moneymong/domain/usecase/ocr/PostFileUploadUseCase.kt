package com.moneymong.moneymong.domain.usecase.ocr

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.ocr.FileUploadEntity
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.repository.ocr.OCRRepository
import javax.inject.Inject

class PostFileUploadUseCase @Inject constructor(
    private val ocrRepository: OCRRepository
): BaseUseCase<FileUploadParam, Result<FileUploadEntity>>() {
    override suspend fun invoke(data: FileUploadParam): Result<FileUploadEntity> =
        ocrRepository.postFileUpload(data)
}