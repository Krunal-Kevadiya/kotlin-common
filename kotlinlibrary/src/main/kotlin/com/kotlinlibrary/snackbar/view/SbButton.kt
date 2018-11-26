package com.kotlinlibrary.snackbar.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.kotlinlibrary.R

internal class SbButton : TextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.style.SbButtonStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}