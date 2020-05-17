package com.kotlinlibrary.snackbar.view

import android.content.Context
import android.util.AttributeSet
import com.kotlinlibrary.R
import androidx.appcompat.widget.AppCompatTextView;

internal class SbButton : AppCompatTextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.style.SbButtonStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}