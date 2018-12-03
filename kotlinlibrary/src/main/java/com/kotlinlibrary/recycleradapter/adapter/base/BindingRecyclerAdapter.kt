package com.kotlinlibrary.recycleradapter.adapter.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.exception.ViewHolderNotRegisteredException
import com.kotlinlibrary.recycleradapter.util.inflateDatabinding
import kotlin.reflect.KClass

abstract class BindingRecyclerAdapter : BaseRecyclerAdapter() {

    private var holders = mutableMapOf<Int, Pair<Int, (ViewDataBinding) -> RecyclerView.ViewHolder>>()

    fun <ItemT : Any, HolderT : RecyclerView.ViewHolder> addViewHolder(itemClass: KClass<ItemT>, @LayoutRes layoutRes: Int, factory: (ViewDataBinding) -> HolderT) {
        holders[itemClass.hashCode()] = layoutRes to factory
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            holders[viewType]
                    ?.let { (layoutRes, factory) -> factory(parent.inflateDatabinding(layoutRes)) }
                    ?: throw ViewHolderNotRegisteredException(getItemClassName(viewType))
}