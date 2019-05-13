package com.kotlinlibrary.utils.ktx.dialog

import android.content.DialogInterface
import com.kotlinlibrary.utils.getContextFrom

fun Any.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        onClick: (DialogInterface, Int) -> Unit
) {
    with(AndroidAlertBuilder(getContextFrom(this))) {
        if (title != null) {
            this.title = title
        }
        items(items, onClick)
        show()
    }
}