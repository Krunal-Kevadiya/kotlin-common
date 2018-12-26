package com.kotlinlibrary.recycleradapter.kidadapter.simple

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.kidadapter.base.KidList
import com.kotlinlibrary.recycleradapter.kidadapter.exceptions.UndefinedLayout

class SingleKidAdapterConfiguration<T> {
    internal var items = KidList<T>()
        private set
    internal var layoutManager: RecyclerView.LayoutManager? = null
        private set
    @LayoutRes
    internal var layoutResId: Int = -1
        private set
    internal var bindHolder: View.(T) -> Unit = {}
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

    fun bind(block: View.(T) -> Unit) {
        this.bindHolder = block
    }

    internal fun validate() {
        when {
            layoutResId == -1 && items.isNotEmpty() -> throw UndefinedLayout("Adapter layout is not set, " +
                    "please declare it with withLayoutResId() function")
        }
    }
}