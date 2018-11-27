package com.kotlinlibrary.widget

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList
import java.util.Random

class JustifiedTextView : TextView {
    // Object that helps us to measure the words and characters like spaces.
    private lateinit var mPaint: Paint
    // Thin space character that will fill the spaces
    private var mThinSpace = "\u200A"
    // String that will storage the text with the inserted spaces
    private var justifiedText = ""
    // Float that represents the actual width of a sentence
    private var sentenceWidth = 0f
    // Integer that counts the spaces needed to fill the line being processed
    private var whiteSpacesNeeded = 0
    // Integer that counts the actual amount of words in the sentence
    private var wordsInThisSentence = 0
    // ArrayList of Strings that will contain the words of the sentence being processed
    private var temporalLine = ArrayList<String>()
    private var mViewWidth: Int = 0
    private var mThinSpaceWidth: Float = 0.toFloat()
    private var mWhiteSpaceWidth: Float = 0.toFloat()

    // Default Constructors!
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun setJustifiedText(text: String) {
        justifiedText = ""
        resetText(text)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        resetText(this.text.toString())
    }

    private fun resetText(text: String) {
        val params = this.layoutParams
        val words = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        mPaint = this.paint

        mViewWidth = this.measuredWidth - (paddingLeft + paddingRight)
        /*This class won't justify the text if the TextView has wrap_content as width
        And won't repeat the process of justify text if it's already done.
        AND! won't justify the text if the view width is 0*/
        if (params.width != ViewGroup.LayoutParams.WRAP_CONTENT && mViewWidth > 0 && words.size > 0 && justifiedText.isEmpty()) {
            mThinSpaceWidth = mPaint.measureText(mThinSpace)
            mWhiteSpaceWidth = mPaint.measureText(" ")

            for (word in words) {
                val containsNewLine = word.contains("\n") || word.contains("\r")

                if (containsNewLine) {
                    val splitted = word.split("(?<=\\n)".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    for (splitWord in splitted) {
                        processWord(splitWord, splitWord.contains("\n"))
                    }
                } else
                    processWord(word, false)
            }
            justifiedText += joinWords(temporalLine)
        }

        if (!justifiedText.isEmpty())
            this.text = justifiedText
    }

    private fun processWord(word: String, containsNewLine: Boolean) {
        if (sentenceWidth + mPaint.measureText(word) < mViewWidth) {
            temporalLine.add(word)
            wordsInThisSentence++
            temporalLine.add(if (containsNewLine) "" else " ")
            sentenceWidth += mPaint.measureText(word) + mWhiteSpaceWidth
            if (containsNewLine) {
                justifiedText += joinWords(temporalLine)
                resetLineValues()
            }
        } else {
            while (sentenceWidth < mViewWidth) {
                sentenceWidth += mThinSpaceWidth
                if (sentenceWidth < mViewWidth)
                    whiteSpacesNeeded++
            }

            if (wordsInThisSentence > 1)
                insertWhiteSpaces(whiteSpacesNeeded, wordsInThisSentence, temporalLine)

            justifiedText += joinWords(temporalLine)
            resetLineValues()

            if (containsNewLine) {
                justifiedText += word
                wordsInThisSentence = 0
                return
            }
            temporalLine.add(word)
            wordsInThisSentence = 1
            temporalLine.add(" ")
            sentenceWidth += mPaint.measureText(word) + mWhiteSpaceWidth
        }
    }

    // Method that resets the values of the actual line being processed
    private fun resetLineValues() {
        temporalLine.clear()
        sentenceWidth = 0f
        whiteSpacesNeeded = 0
        wordsInThisSentence = 0
    }

    // Function that joins the words of the ArrayList
    private fun joinWords(words: ArrayList<String>): String {
        var sentence = ""
        for (word in words) {
            sentence += word
        }
        return sentence
    }

    /*Method that inserts spaces into the words to make them fix perfectly in the width of the
    view. I know I'm a genius naming stuff :)*/
    private fun insertWhiteSpaces(argWhiteSpacesNeeded: Int, wordsInThisSentence: Int, sentence: ArrayList<String>) {
        var whiteSpacesNeeded = argWhiteSpacesNeeded

        if (whiteSpacesNeeded == 0)
            return

        if (whiteSpacesNeeded == wordsInThisSentence) {
            var i = 1
            while (i < sentence.size) {
                sentence[i] = sentence[i] + mThinSpace
                i += 2
            }
        } else if (whiteSpacesNeeded < wordsInThisSentence) {
            for (i in 0 until whiteSpacesNeeded) {
                val randomPosition = getRandomEvenNumber(sentence.size - 1)
                sentence[randomPosition] = sentence[randomPosition] + mThinSpace
            }
        } else if (whiteSpacesNeeded > wordsInThisSentence) {
            /*I was using recursion to achieve this... but when you tried to watch the preview,
            Android Studio couldn't show any preview because a StackOverflow happened.
            So... it ended like this, with a wild while xD.*/
            while (whiteSpacesNeeded > wordsInThisSentence) {
                var i = 1
                while (i < sentence.size - 1) {
                    sentence[i] = sentence[i] + mThinSpace
                    i += 2
                }
                whiteSpacesNeeded -= wordsInThisSentence - 1
            }
            if (whiteSpacesNeeded == 0)
                return

            if (whiteSpacesNeeded == wordsInThisSentence) {
                var i = 1
                while (i < sentence.size) {
                    sentence[i] = sentence[i] + mThinSpace
                    i += 2
                }
            } else if (whiteSpacesNeeded < wordsInThisSentence) {
                for (i in 0 until whiteSpacesNeeded) {
                    val randomPosition = getRandomEvenNumber(sentence.size - 1)
                    sentence[randomPosition] = sentence[randomPosition] + mThinSpace
                }
            }
        }
    }

    // Gets a random number, it's part of the algorithm... don't blame me.
    private fun getRandomEvenNumber(max: Int): Int {
        val rand = Random()
        // nextInt is normally exclusive of the top value,
        return rand.nextInt(max) and 1.inv()
    }
}
