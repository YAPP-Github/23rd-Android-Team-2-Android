package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.BuildConfig
import com.moneymong.moneymong.network.request.ocr.DocumentRequest
import com.moneymong.moneymong.network.response.ocr.DocumentResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ClovaApi {

    @POST("document/receipt")
    suspend fun documentOCR(
        @Header("X-OCR-SECRET") secret: String = BuildConfig.CLOVA_OCR_DOCUMENT_SECRET,
        @Body body: DocumentRequest
    ): Result<DocumentResponse>
}