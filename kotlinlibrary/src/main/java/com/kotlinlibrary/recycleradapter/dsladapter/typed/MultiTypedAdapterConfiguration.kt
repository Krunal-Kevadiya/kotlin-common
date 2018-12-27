package com.kotlinlibrary.recycleradapter.dsladapter.typed

import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.dsladapter.base.DiffUtilCallback
import com.kotlinlibrary.recycleradapter.dsladapter.base.AdapterList
import com.kotlinlibrary.recycleradapter.dsladapter.exceptions.UndefinedLayout

class MultiTypedAdapterConfiguration {
    private var internalItems: AdapterList<Any> = AdapterList()

    val viewTypes = mutableListOf<AdapterViewType<Any>>()
    internal var layoutManager: RecyclerView.LayoutManager? = null

    inline fun <reified T> withViewType(noinline block: AdapterViewTypeConfiguration<T>.() -> Unit) {
        viewTypes.add(AdapterViewType<T>(T::class.hashCode(), block) as AdapterViewType<Any>)
    }

    fun withLayoutManager(block: () -> RecyclerView.LayoutManager?) {
        layoutManager = block()
    }

    internal fun getAllItems(): AdapterList<Any> = internalItems

    inline fun <reified T> withItems(items: MutableList<T>) {
        setInternalItems(items as MutableList<Any>)
    }

    fun setInternalItems(items: MutableList<Any>) {
        diffCallback = this.internalItems.reset(items)
    }

    private var contentComparator: ((Any, Any) -> Boolean)? = null
    private var itemsComparator: ((Any, Any) -> Boolean)? = null
    private var diffCallback: DiffUtilCallback<Any>? = null
        get() = field?.apply {
            this.contentComparator = this@MultiTypedAdapterConfiguration.contentComparator
            this.itemsComparator = this@MultiTypedAdapterConfiguration.itemsComparator
        }

    internal fun validate() {
        when {
            viewTypes.firstOrNull { it.configuration.layoutResId == -1 } != null -> throw UndefinedLayout(
                "Adapter layout is not set, please declare it for each AdapterViewType with withLayoutResId() function"
            )
        }
    }
}