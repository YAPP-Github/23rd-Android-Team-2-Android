package com.moneymong.moneymong.design_system.component.textfield.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.util.PriceType

class PriceVisualTransformation(private val type: PriceType) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val number = text.text
        var numberWithComma = ""
        for (i in number.indices) {
            numberWithComma += number[i]
            if (i != number.lastIndex && (number.lastIndex - i) % 3 == 0) {
                numberWithComma += ","
            }
        }
        val out = if (numberWithComma.isEmpty()) "" else "${type.symbol}${numberWithComma}원"

        val priceOffsetTranslator = object : OffsetMapping {
            val symbolLength = type.symbol.length

            override fun originalToTransformed(offset: Int): Int {
                val cursorPoint = offset

                if (text.isEmpty()) return 0
                if (cursorPoint == text.length) return numberWithComma.length + symbolLength

                val rightCursorPointCount = text.length - cursorPoint
                val commaCountToTheRight =
                    if (rightCursorPointCount % 3 == 0) {
                        rightCursorPointCount / 3 - 1
                    } else {
                        rightCursorPointCount / 3
                    }
                return (numberWithComma.length - rightCursorPointCount - commaCountToTheRight) + symbolLength
            }

            override fun transformedToOriginal(offset: Int): Int {
                val cursorPoint = offset

                if (cursorPoint in 0..1) return if (type == PriceType.None) cursorPoint else 0
                if (cursorPoint == out.length) return text.length

                val commaCount = numberWithComma.count { it == ',' }
                val rightCursorPointCount = (numberWithComma.length + symbolLength) - cursorPoint
                val commaCountToTheRight = rightCursorPointCount / 4
                val leftCommaCount = commaCount - commaCountToTheRight
                return (cursorPoint - leftCommaCount - symbolLength)
            }
        }

        return TransformedText(
            text = AnnotatedString(out),
            offsetMapping = priceOffsetTranslator
        )
    }
}

