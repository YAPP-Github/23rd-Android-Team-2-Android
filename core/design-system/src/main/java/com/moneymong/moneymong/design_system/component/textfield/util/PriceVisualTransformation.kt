package com.moneymong.moneymong.design_system.component.textfield.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PriceVisualTransformation(private val type: MDSNumberTextFieldType) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val amount = text.text
        var numberWithComma = ""
        for (i in amount.indices) {
            numberWithComma += amount[i]
            if (i != amount.length - 1 && (amount.length - 1 - i) % 3 == 0) {
                numberWithComma += ","
            }
        }
        val out =
            if (numberWithComma.isEmpty()) numberWithComma else "${type.symbol}${numberWithComma}원"

        val priceOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val cursorPoint = offset

                if (text.isEmpty()) return 0
                if (cursorPoint == text.length) return numberWithComma.length + 1

                val rightCursorPointCount = text.length - cursorPoint
                val commaCountToTheRight =
                    if (rightCursorPointCount % 3 == 0) {
                        rightCursorPointCount / 3 - 1
                    } else {
                        rightCursorPointCount / 3
                    }
                return (numberWithComma.length - rightCursorPointCount - commaCountToTheRight) + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                val cursorPoint = offset

                if (cursorPoint in 0..1) return 0
                if (cursorPoint == out.length) return text.length

                val symbolCount = type.symbol.length
                val commaCount = numberWithComma.count { it == ',' }
                val rightCursorPointCount = numberWithComma.length - cursorPoint
                val commaCountToTheRight = rightCursorPointCount / 4
                val leftCommaCount = commaCount - commaCountToTheRight
                return (cursorPoint - leftCommaCount - symbolCount)
            }
        }

        return TransformedText(
            text = AnnotatedString(out),
            offsetMapping = priceOffsetTranslator
        )
    }
}

