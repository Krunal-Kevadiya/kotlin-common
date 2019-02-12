package com.kotlinlibrary.recycleradapter.typed

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

class AdapterViewTypeConfiguration<T> {
    @LayoutRes
    internal var layoutResId: Int = -1
        private set

    internal var layoutBr: Int = -1
        private set

    internal var bindHolder: View.(Int, Any) -> Unit = {_, _ -> }

    internal var bindBindingHolder: Any.(Int, Any) -> Unit = {_, _ -> }

    internal var clickResId = ArrayList<Int>()
    internal var clickListener: (Int, T) -> Unit = {_, _ -> }
        private set

    fun withLayoutResId(@LayoutRes layoutResId: Int) {
        this.layoutResId = layoutResId
    }

    @Suppress("UNCHECKED_CAST")
    fun onBind(block: View.(Int, T) -> Unit) {
        bindHolder = (block as View.(Int, Any) -> Unit)
    }

    @Suppress("UNCHECKED_CAST")
    fun <V: ViewDataBinding> onBind(brId: Int, block: V.(Int, T) -> Unit) {
        layoutBr = brId
        bindBindingHolder = (block as Any.(Int, Any) -> Unit)
    }

    fun onClick(@IdRes vararg resId: Int, block: (Int, T) -> Unit) {
        clickResId.addAll(resId.toList())
        this.clickListener = block
    }
}