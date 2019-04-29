package com.kotlinlibrary.recycleradapter.simple

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.BindDsl
import com.kotlinlibrary.recycleradapter.ConfigurationDsl
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

    internal var bindHolder: View.(Int, T) -> Unit = {_ , _ -> }
        private set

    internal var bindBindingHolder: Any.(Int, T) -> Unit = {_, _ -> }
        private set

    internal var clickResId = ArrayList<Int>()
    internal var clickListener: (Int, Int, T) -> Unit = {_, _, _ -> }
        private set

    internal var isDiffUtils: Boolean = true

    internal var contentComparator: ((T, T) -> Boolean)? = null

    internal var itemsComparator: ((T, T) -> Boolean)? = null

    /**
     * Set adapter view type items here.
     * @param items items to be inserted in RecyclerView.
     */
    @ConfigurationDsl
    fun withItems(items: MutableList<T>) {
        this.items.reset(items)
    }

    /**
     * Set adapter view type item here.
     * @param item item to be inserted in RecyclerView
     */
    @ConfigurationDsl
    fun withItem(item: T) {
        this.items.reset(mutableListOf(item))
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
     * Set layout resource id which will be bounded to actual view type
     * @param layoutResId desired layout resource id
     */
    @ConfigurationDsl
    fun withLayoutResId(@LayoutRes layoutResId: Int) {
        this.layoutResId = layoutResId
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
    fun withContentComparator(contentComparator: (T, T) -> Boolean) {
        this.contentComparator = contentComparator
    }

    /**
     * Equivalent to [DiffUtil.Callback.areItemsTheSame]. Method call is optional, by default in compare objects
     * by equals, if you want another behavior then please implement it here.
     */
    @ConfigurationDsl
    fun withItemsComparator(itemsComparator: (T, T) -> Boolean) {
        this.itemsComparator = itemsComparator
    }

    /**
     * Set action which must been called when [ecyclerView.Adapter.onBindViewHolder]
     * @param block is executed in [RecyclerView.ViewHolder.itemView] context
     * @param block.item item from adapter list at position, equivalent of itemsList.get(position]
     * @param block.index position
     */
    @BindDsl
    fun onBind(block: View.(index: Int, item: T) -> Unit) {
        this.bindHolder = block
    }

    /**
     * Set action which must been called when [ecyclerView.Adapter.onBindViewHolder]
     * @param block is executed in [RecyclerView.ViewHolder.itemView] context
     * @param block.item item from adapter list at position, equivalent of itemsList.get(position]
     * @param block.index position
     */
    @BindDsl
    @Suppress("UNCHECKED_CAST")
    fun <V: ViewDataBinding> onBind(brId: Int, block: V.(index: Int, item: T) -> Unit) {
        this.layoutBr = brId
        this.bindBindingHolder = block as Any.(Int, T) -> Unit
    }

    fun onClick(@IdRes vararg resId: Int, block: (Int, Int, T) -> Unit) {
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