package com.moneymong.moneymong.design_system.component.textfield.time

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TimeVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val origin = text.text
        val out = origin.formatTime()

        val timeOffsetTranslator = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                return when (offset) {
                    in 0..1 -> offset
                    2 -> if (origin.length > 2) offset + 1 else offset
                    3 -> offset + 1
                    4 -> if (origin.length > 4) offset + 2 else offset + 1
                    else -> offset + 2
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when (offset) {
                    in 0..2 -> offset
                    in 3..5 -> offset - 1
                    else -> offset - 2
                }
            }
        }

        return TransformedText(
            text = AnnotatedString(out),
            offsetMapping = timeOffsetTranslator
        )
    }

    private fun String.formatTime(): String {
        val hour = this.take(2)
        val minute = this.take(4).drop(2)
        val second = this.drop(4)

        return when (this.length) {
            in 0..2 -> hour
            in 3..4 -> "$hour:$minute"
            else -> "$hour:$minute:$second"
        }
    }
}