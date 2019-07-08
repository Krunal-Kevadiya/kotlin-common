package com.kotlinlibrary.buttonview.listener

interface ProgressButtonAnimationListener {
    fun onRevertMorphingEnd()

    fun onApplyMorphingEnd()

    fun onShowProgress()

    fun onSetText()

    fun onShowResultState()
}