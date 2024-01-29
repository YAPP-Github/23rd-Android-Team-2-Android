package com.moneymong.moneymong.domain.param.ocr

import java.io.File

data class FileUploadParam(
    val file: File,
    val dirName: String
)
