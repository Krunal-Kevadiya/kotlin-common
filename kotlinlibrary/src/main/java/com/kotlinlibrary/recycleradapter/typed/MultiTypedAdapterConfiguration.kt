package com.kotlinlibrary.recycleradapter.typed

import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.base.AdapterList
import com.kotlinlibrary.recycleradapter.exceptions.UndefinedLayout

class MultiTypedAdapterConfiguration {
    internal var items: AdapterList<Any> =
        AdapterList()
        private set

    val viewTypes = mutableListOf<AdapterViewType<Any>>()

    internal var layoutManager: RecyclerView.LayoutManager? = null
        private set

    internal var contentComparator: ((Any, Any) -> Boolean)? = null

    internal var itemsComparator: ((Any, Any) -> Boolean)? = null

    inline fun <reified T> withViewType(noinline block: AdapterViewTypeConfiguration<T>.() -> Unit) {
        viewTypes.add(
            AdapterViewType<T>(
                T::class.hashCode(),
                block
            ) as AdapterViewType<Any>
        )
    }

    fun withLayoutManager(block: () -> RecyclerView.LayoutManager?) {
        layoutManager = block()
    }

    fun withItems(items: MutableList<Any>) {
        this.items.reset(items)
    }

    fun withItem(item: Any) {
        this.items.reset(mutableListOf(item))
    }

    internal fun validate() {
        when {
            viewTypes.firstOrNull { it.configuration.layoutResId == -1 } != null -> throw UndefinedLayout(
                "Adapter layout is not set, please declare it for each AdapterViewType with withLayoutResId() function"
            )
        }
    }
}