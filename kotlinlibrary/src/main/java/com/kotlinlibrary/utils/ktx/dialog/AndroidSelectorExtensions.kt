package com.kotlinlibrary.utils.ktx.dialog

import android.content.DialogInterface
import com.kotlinlibrary.utils.getContextFromSource

fun Any.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        onClick: (DialogInterface, Int) -> Unit
) {
    with(AndroidAlertBuilder(getContextFromSource(this))) {
        if (title != null) {
            this.title = title
        }
        items(items, onClick)
        show()
    }
}