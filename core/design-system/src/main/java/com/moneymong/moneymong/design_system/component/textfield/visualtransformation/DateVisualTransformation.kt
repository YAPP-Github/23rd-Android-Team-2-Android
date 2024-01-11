package com.moneymong.moneymong.design_system.component.textfield.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val origin = text.text
        val out = origin.formatDate()

        val dateOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when (offset) {
                    in 0..4 -> offset
                    in 5..6 -> offset + 1
                    else -> offset + 2
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when (offset) {
                    in 0..4 -> offset
                    in 5..7 -> offset - 1
                    else -> offset - 2
                }
            }
        }

        return TransformedText(
            text = AnnotatedString(out),
            offsetMapping = dateOffsetTranslator
        )
    }


    private fun String.formatDate(): String {
        require(this.length <= 8) {
            "날짜는 8글자보다 길게 입력받을 수 없어요. 글자 수 제한이 필요합니다."
        }
        val year = this.take(4)
        val month = this.take(6).drop(4)
        val day = this.drop(6)

        return when (this.length) {
            in 0..4 -> year
            in 5..6 -> "$year/$month"
            else -> "$year/$month/$day"
        }
    }
}