package com.kotlinlibrary.recycleradapter.kidadapter.typed

import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.kidadapter.base.KidDiffUtilCallback
import com.kotlinlibrary.recycleradapter.kidadapter.base.KidList
import com.kotlinlibrary.recycleradapter.kidadapter.exceptions.UndefinedLayout
import com.kotlinlibrary.recycleradapter.kidadapter.simple.SingleKidAdapterConfiguration

class TypedKidAdapterConfiguration {
    private var internalItems: KidList<Any> = KidList()

    internal val viewTypes = mutableListOf<AdapterViewType<Any>>()
    internal var layoutManager: RecyclerView.LayoutManager? = null

    fun withViewType(block: AdapterViewTypeConfiguration.() -> Unit) {
        viewTypes.add(AdapterViewType(block))
    }

    fun withLayoutManager(block: () -> RecyclerView.LayoutManager?) {
        layoutManager = block()
    }

    fun fromSimpleConfiguration(block: SingleKidAdapterConfiguration<*>.() -> Unit): TypedKidAdapterConfiguration {
        return TypedKidAdapterConfiguration().apply {
            val adapterConfiguration = SingleKidAdapterConfiguration<Any>().apply(block)
            withLayoutManager { adapterConfiguration.layoutManager }
            withViewType {
                bind<Any> {
                    adapterConfiguration.bindHolder(this, it)
                }
            }
        }
    }

    internal fun getAllItems(): KidList<Any> = internalItems

    inline fun <reified T> withItems(items: MutableList<T>) {
        setInternalItems(items as MutableList<Any>)
    }

    fun setInternalItems(items: MutableList<Any>) {
        diffCallback = this.internalItems.reset(items)
    }

    private var contentComparator: ((Any, Any) -> Boolean)? = null
    private var itemsComparator: ((Any, Any) -> Boolean)? = null
    internal var diffCallback: KidDiffUtilCallback<Any>? = null
        get() = field?.apply {
            this.contentComparator = this@TypedKidAdapterConfiguration.contentComparator
            this.itemsComparator = this@TypedKidAdapterConfiguration.itemsComparator
        }

    internal fun validate() {
        when {
            viewTypes.firstOrNull { it.configuration.layoutResId == -1 } != null -> throw UndefinedLayout(
                "Adapter layout is not set, please declare it for each AdapterViewType with withLayoutResId() function"
            )
        }
    }
}