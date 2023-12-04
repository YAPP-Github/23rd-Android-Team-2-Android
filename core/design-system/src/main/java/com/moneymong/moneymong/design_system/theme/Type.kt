package com.moneymong.moneymong.design_system.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.moneymong.moneymong.design_system.R


private val SpoqaHanSansNeo = FontFamily(
    Font(R.font.spoqa_han_sans_neo_bold, FontWeight.Bold),
    Font(R.font.spoqa_han_sans_neo_medium, FontWeight.Medium),
    Font(R.font.spoqa_han_sans_neo_regular, FontWeight.Normal),
)

private val MMLetterSpacing = (-0.6).sp

val Heading5 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = MMLetterSpacing,
)

val Heading4 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 45.sp,
    lineHeight = 52.sp,
    letterSpacing = MMLetterSpacing,
)

val Heading3 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 36.sp,
    lineHeight = 44.sp,
    letterSpacing = MMLetterSpacing,
)

val Heading2 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = MMLetterSpacing,
)

val Heading1 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = MMLetterSpacing,
)

val Body5 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = MMLetterSpacing,
)

val Body4 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = MMLetterSpacing,
)

val Body3 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = MMLetterSpacing,
)

val Body2 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = MMLetterSpacing,
)

val Body1 = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = MMLetterSpacing,
)

val Caption = TextStyle(
    fontFamily = SpoqaHanSansNeo,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = MMLetterSpacing,
)