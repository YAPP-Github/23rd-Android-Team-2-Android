package com.moneymong.moneymong.feature.sign.util

enum class Grade(val text: String) {
    FIRST("1학년"),
    SECOND("2학년"),
    THIRD("3학년"),
    FOURTH("4학년"),
    FIFTH_OR_ABOVE("5학년 이상")
}

fun getGradeNumber(grade : String) : Int{
    return when(grade){
        "1학년" -> 1
        "2학년" -> 2
        "3학년" -> 3
        "4학년" -> 4
        else -> 5
    }
}