package com.kotlinlibrary.creditcardview

import kotlin.collections.ArrayList
import kotlin.collections.dropLastWhile
import kotlin.collections.forEach
import kotlin.collections.map
import kotlin.collections.plusAssign
import kotlin.collections.sumBy
import kotlin.collections.toTypedArray

class CardValidator(cardNo: String) {
    companion object {
        const val CARD_SEPARATORS: String = " "
    }

    private var cardNumber: String? = null
    private var checkDigit: Int = 0
    val isValidCardNumber: Boolean
        get() {
            if (cardNumber!!.trim { it <= ' ' }.isEmpty())
                return false
            dropLastNumber()
            return (addAllNumber() + checkDigit) % 10 == 0
        }

    init {
        this.cardNumber = cardNo.replace(CARD_SEPARATORS, "")
    }

    private fun dropLastNumber() {
        checkDigit = Integer.parseInt(cardNumber!!.substring(cardNumber!!.length - 1))
        this.cardNumber = cardNumber!!.substring(0, cardNumber!!.length - 1)
    }

    private fun addAllNumber(): Int {
        return (cardNumber!!.indices).sumBy {
            if (cardNumber!!.length % 2 != 0)
                multiplyOddByTwo(it + 1, Integer.parseInt(cardNumber!![it].toString()))
            else
                multiplyOddByTwo(it, Integer.parseInt(cardNumber!![it].toString()))
        }
    }

    private fun multiplyOddByTwo(i: Int, digit: Int): Int {
        return if (i % 2 == 0) digit else subtractNine(digit * 2)
    }

    private fun subtractNine(digit: Int): Int {
        return if (digit > 9) digit - 9 else digit
    }

    @Suppress("NAME_SHADOWING")
    fun guessCard(): Card? {
        for (cardEnum in CardEnum.values()) {
            val startWiths =
                ArrayList(listOf(*cardEnum.startWith.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
            startWiths
                .map { cardStartIndex -> cardStartIndex.trim { it <= ' ' } }
                .forEach { cardStartIndex ->
                    if (cardStartIndex.contains("-")) {
                        val range = cardStartIndex.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val start = java.lang.Long.parseLong(range[0].trim { it <= ' ' })
                        val end = java.lang.Long.parseLong(range[1].trim { it <= ' ' })

                        if (start.toString().length <= cardNumber!!.length) {
                            val cardNumberSub = cardNumber!!.substring(0, start.toString().length)
                            if (cardNumberSub.toLong() in start..end) {
                                return Card(
                                    cardEnum.cardName,
                                    cardEnum.icon,
                                    startWiths,
                                    fetchPossibleLength(cardEnum),
                                    cardEnum.cvv
                                )
                            }
                        }
                    } else {
                        if (cardNumber!!.startsWith(cardStartIndex)) {
                            return Card(
                                cardEnum.cardName,
                                cardEnum.icon,
                                startWiths,
                                fetchPossibleLength(cardEnum),
                                cardEnum.cvv
                            )
                        }
                    }
                }
        }

        return null
    }

    private fun fetchPossibleLength(cardEnum: CardEnum): ArrayList<Int> {
        val lengths =
            ArrayList(listOf(*cardEnum.length.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
        val possibleLengths = ArrayList<Int>()
        for (length in lengths) {
            if (length.contains("-")) {
                val range = length.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val start = Integer.parseInt(range[0].trim { it <= ' ' })
                val end = Integer.parseInt(range[1].trim { it <= ' ' })
                possibleLengths += start..end
            } else {
                possibleLengths.add(Integer.parseInt(length.trim { it <= ' ' }))
            }
        }
        return possibleLengths
    }
}
