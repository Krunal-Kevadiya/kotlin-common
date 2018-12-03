package com.kotlinlibrary.creditcardview

class Card(
    val cardName: String,
    val drawable: Int,
    val startWith: ArrayList<String>,
    val possibleLengths: ArrayList<Int>,
    val cvvLength: Int
) {
    val maxLength: Int
        get() = possibleLengths[possibleLengths.size - 1]
}
