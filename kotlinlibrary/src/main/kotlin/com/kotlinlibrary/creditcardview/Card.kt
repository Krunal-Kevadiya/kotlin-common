package com.kotlinlibrary.creditcardview

class Card(val cardName: String, val possibleLengths: ArrayList<Int>, val drawable: Int, val cvvLength: Int) {
    val maxLength: Int
        get() = possibleLengths[possibleLengths.size - 1]
}
