package com.moneymong.moneymong.design_system.component.textfield.date

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