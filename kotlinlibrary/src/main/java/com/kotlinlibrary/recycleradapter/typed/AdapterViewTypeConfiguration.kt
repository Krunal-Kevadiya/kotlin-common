package com.kotlinlibrary.recycleradapter.typed

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.kotlinlibrary.recycleradapter.BindDsl
import com.kotlinlibrary.recycleradapter.ConfigurationDsl

class AdapterViewTypeConfiguration<T> {
    @LayoutRes
    internal var layoutResId: Int = -1
        private set

    internal var layoutBr: Int = -1
        private set

    internal var bindHolder: View.(Int, Any) -> Unit = {_, _ -> }

    internal var bindBindingHolder: Any.(Int, Any) -> Unit = {_, _ -> }

    internal var clickResId = ArrayList<Int>()
    internal var clickListener: (Int, Int, T) -> Unit = {_, _, _ -> }
        private set

    /**
     * Set layout resource id which will be bounded to actual view type
     * @param layoutResId desired layout resource id
     */
    @ConfigurationDsl
    fun withLayoutResId(@LayoutRes layoutResId: Int) {
        this.layoutResId = layoutResId
    }

    /**
     * Set action which must been called when [ecyclerView.Adapter.onBindViewHolder]
     * @param block is executed in [RecyclerView.ViewHolder.itemView] context
     * @param block.item item from adapter list at position, equivalent of itemsList.get(position]
     * @param block.index position
     */
    @BindDsl
    @Suppress("UNCHECKED_CAST")
    fun onBind(block: View.(Int, T) -> Unit) {
        bindHolder = (block as View.(Int, Any) -> Unit)
    }

    /**
     * Set action which must been called when [ecyclerView.Adapter.onBindViewHolder]
     * @param block is executed in [RecyclerView.ViewHolder.itemView] context
     * @param block.item item from adapter list at position, equivalent of itemsList.get(position]
     * @param block.index position
     */
    @Suppress("UNCHECKED_CAST")
    fun <V: ViewDataBinding> onBind(brId: Int, block: V.(Int, T) -> Unit) {
        layoutBr = brId
        bindBindingHolder = (block as Any.(Int, Any) -> Unit)
    }

    fun onClick(@IdRes vararg resId: Int, block: (Int, Int, T) -> Unit) {
        clickResId.addAll(resId.toList())
        this.clickListener = block
    }
}