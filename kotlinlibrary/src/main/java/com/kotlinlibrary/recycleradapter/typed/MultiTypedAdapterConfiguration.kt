package com.kotlinlibrary.recycleradapter.typed

import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.ConfigurationDsl
import com.kotlinlibrary.recycleradapter.base.AdapterList
import com.kotlinlibrary.recycleradapter.base.DiffUtilCallback
import com.kotlinlibrary.recycleradapter.exceptions.UndefinedLayout

class MultiTypedAdapterConfiguration {
    internal var items: AdapterList<Any> = AdapterList()
        private set

    val viewTypes = mutableListOf<AdapterViewType<Any>>()

    internal var layoutManager: RecyclerView.LayoutManager? = null
        private set

    internal var isDiffUtils: Boolean = true

    internal var contentComparator: ((Any, Any) -> Boolean)? = null

    internal var itemsComparator: ((Any, Any) -> Boolean)? = null

    internal var diffCallback: DiffUtilCallback<Any>? = null
        get() = field?.apply {
            this.contentComparator = this@MultiTypedAdapterConfiguration.contentComparator
            this.itemsComparator = this@MultiTypedAdapterConfiguration.itemsComparator
        }

    /**
     * Declare adapter view type.
     * @param block configure your view type here.
     */
    @ConfigurationDsl
    inline fun <reified T> withViewType(noinline block: AdapterViewTypeConfiguration<T>.() -> Unit) {
        viewTypes.add(
            AdapterViewType<T>(
                T::class.hashCode(),
                block
            ) as AdapterViewType<Any>
        )
    }

    /**
     * Set [androidx.recyclerview.widget.RecyclerView.LayoutManager] of a current [RecyclerView].
     * By default it is a vertical [androidx.recyclerview.widget.LinearLayoutManager]
     */
    @ConfigurationDsl
    fun withLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        this.layoutManager = layoutManager
    }

    /**
     * Set [RecyclerView.LayoutManager] of a current [RecyclerView].
     * By default it is a vertical [LinearLayoutManager]
     * @param block configure your layout manager here
     */
    @ConfigurationDsl
    fun withLayoutManager(block: () -> RecyclerView.LayoutManager?) {
        layoutManager = block()
    }

    /**
     * Set diffUtils enable or disable of a current [RecyclerView]
     * @param block configure your diffUtils toggle here
     */
    @ConfigurationDsl
    fun withDiffUtils(block: () -> Boolean) {
        isDiffUtils = block()
    }

    /**
     * Equivalent to [DiffUtil.Callback.areContentsTheSame]. Method call is optional, by default in compare objects
     * by equals, if you want another behavior then please implement it here.
     */
    @ConfigurationDsl
    fun withContentComparator(block: (Any, Any) -> Boolean) {
        contentComparator = block
    }

    /**
     * Equivalent to [DiffUtil.Callback.areItemsTheSame]. Method call is optional, by default in compare objects
     * by equals, if you want another behavior then please implement it here.
     */
    @ConfigurationDsl
    fun withItemsComparator(block: (Any, Any) -> Boolean) {
        itemsComparator = block
    }

    /**
     * Set adapter view type items here.
     * @param items items to be inserted in RecyclerView.
     */
    @ConfigurationDsl
    fun withItems(items: MutableList<Any>) {
        this.items.reset(items)
    }

    /**
     * Set adapter view type item here.
     * @param item item to be inserted in RecyclerView
     */
    @ConfigurationDsl
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