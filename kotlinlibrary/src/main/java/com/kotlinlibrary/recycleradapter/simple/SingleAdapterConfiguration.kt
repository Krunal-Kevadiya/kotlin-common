package com.kotlinlibrary.recycleradapter.simple

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.base.AdapterList
import com.kotlinlibrary.recycleradapter.exceptions.UndefinedLayout

class SingleAdapterConfiguration<T> {
    internal var items = AdapterList<T>()
        private set

    internal var layoutManager: RecyclerView.LayoutManager? = null
        private set

    @LayoutRes
    internal var layoutResId: Int = -1
        private set

    internal var layoutBr: Int = -1
        private set

    internal var bindHolder: View.(Int, T) -> Unit = {_, _ -> }
        private set

    internal var bindBindingHolder: Any.(Int, T) -> Unit = {_, _ -> }
        private set

    internal var clickResId = ArrayList<Int>()
    internal var clickListener: (Int, T) -> Unit = {_, _ -> }
        private set

    internal var contentComparator: ((T, T) -> Boolean)? = null

    internal var itemsComparator: ((T, T) -> Boolean)? = null

    fun withItems(items: MutableList<T>) {
        this.items.reset(items)
    }

    fun withItem(item: T) {
        this.items.reset(mutableListOf(item))
    }

    fun withLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        this.layoutManager = layoutManager
    }

    fun withLayoutResId(@LayoutRes layoutResId: Int) {
        this.layoutResId = layoutResId
    }

    fun withContentComparator(contentComparator: (T, T) -> Boolean) {
        this.contentComparator = contentComparator
    }

    fun withItemsComparator(itemsComparator: (T, T) -> Boolean) {
        this.itemsComparator = itemsComparator
    }

    fun onBind(block: View.(Int, T) -> Unit) {
        this.bindHolder = block
    }

    @Suppress("UNCHECKED_CAST")
    fun <V: ViewDataBinding> onBind(brId: Int, block: V.(Int, T) -> Unit) {
        this.layoutBr = brId
        this.bindBindingHolder = block as Any.(Int, T) -> Unit
    }

    fun onClick(@IdRes vararg resId: Int, block: (Int, T) -> Unit) {
        clickResId.addAll(resId.toList())
        this.clickListener = block
    }

    internal fun validate() {
        when {
            layoutResId == -1 && items.isNotEmpty() -> throw UndefinedLayout(
                "Adapter layout is not set, " +
                        "please declare it with withLayoutResId() function"
            )
        }
    }
}