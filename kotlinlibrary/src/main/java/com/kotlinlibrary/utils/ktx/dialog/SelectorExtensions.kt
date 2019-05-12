package com.kotlinlibrary.utils.ktx.dialog

import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.Fragment as SupportFragment

inline fun <D : DialogInterface> SupportFragment.selector(
    noinline factory: AlertBuilderFactory<D>,
    title: CharSequence? = null,
    items: List<CharSequence>,
    noinline onClick: (DialogInterface, CharSequence, Int) -> Unit
) = requireContext().selector(factory, title, items, onClick)

inline fun <D : DialogInterface> Fragment.selector(
    noinline factory: AlertBuilderFactory<D>,
    title: CharSequence? = null,
    items: List<CharSequence>,
    noinline onClick: (DialogInterface, CharSequence, Int) -> Unit
) = activity.selector(factory, title, items, onClick)

fun <D : DialogInterface> Context.selector(
    factory: AlertBuilderFactory<D>,
    title: CharSequence? = null,
    items: List<CharSequence>,
    onClick: (DialogInterface, CharSequence, Int) -> Unit
) {
    with(factory(this)) {
        if (title != null) {
            this.title = title
        }
        items(items, onClick)
        show()
    }
}