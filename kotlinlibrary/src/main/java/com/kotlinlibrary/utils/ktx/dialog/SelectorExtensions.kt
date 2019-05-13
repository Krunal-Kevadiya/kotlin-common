package com.kotlinlibrary.utils.ktx.dialog

import android.content.DialogInterface
import com.kotlinlibrary.utils.getContextFrom

fun <D : DialogInterface> Any.selector(
    factory: AlertBuilderFactory<D>,
    title: CharSequence? = null,
    items: List<CharSequence>,
    onClick: (DialogInterface, CharSequence, Int) -> Unit
) {
    with(factory(getContextFrom(this))) {
        if (title != null) {
            this.title = title
        }
        items(items, onClick)
        show()
    }
}