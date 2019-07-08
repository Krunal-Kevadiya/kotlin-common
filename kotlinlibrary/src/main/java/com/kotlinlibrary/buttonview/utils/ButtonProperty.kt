package com.kotlinlibrary.buttonview.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.Button

@SuppressLint("ObsoleteSdkInt")
internal class ButtonProperty(button: Button) {
    val height: Int = button.height
    val width: Int = button.width
    var text: String? = null

    private var drawables: Array<Drawable> = emptyArray()

    var adjustWidth: Int? = null
    var adjustHeight: Int? = null

    init {
        text = button.text.toString()
        drawables = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            button.compoundDrawablesRelative
        } else {
            button.compoundDrawables
        }
    }

    fun getDrawables(): Array<Drawable> {
        return drawables.clone()
    }
}
