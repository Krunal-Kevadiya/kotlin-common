package com.kotlinlibrary.recycleradapter.typed

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

class AdapterViewTypeConfiguration<T> {
    @LayoutRes
    var layoutResId: Int = -1
        private set

    internal var bindHolder: View.(Any) -> Unit = {}

    internal var bindBindingHolder: ViewDataBinding.(Any) -> Unit = {}

    internal var clickResId = ArrayList<Int>()
    internal var clickListener: (Int, T) -> Unit = {_, _ -> }
        private set

    fun withLayoutResId(@LayoutRes layoutResId: Int) {
        this.layoutResId = layoutResId
    }

    fun onBind(block: View.(T) -> Unit) {
        bindHolder = (block as View.(Any) -> Unit)
    }

    fun onBindBinding(block: ViewDataBinding.(T) -> Unit) {
        bindBindingHolder = (block as ViewDataBinding.(Any) -> Unit)
    }

    fun onClick(@IdRes vararg resId: Int, block: (Int, T) -> Unit) {
        clickResId.addAll(resId.toList())
        this.clickListener = block
    }
}