package com.kotlinlibrary.utils.ktx.dialog

import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.Fragment as SupportFragment

inline fun SupportFragment.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        noinline onClick: (DialogInterface, Int) -> Unit
) = requireContext().selector(title, items, onClick)

inline fun Fragment.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        noinline onClick: (DialogInterface, Int) -> Unit
) = activity.selector(title, items, onClick)

fun Context.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        onClick: (DialogInterface, Int) -> Unit
) {
    with(AndroidAlertBuilder(this)) {
        if (title != null) {
            this.title = title
        }
        items(items, onClick)
        show()
    }
}