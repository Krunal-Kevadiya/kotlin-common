package com.kotlinlibrary.recycleradapter.adapter.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.exception.ViewHolderNotRegisteredException
import com.kotlinlibrary.recycleradapter.util.inflate
import kotlin.reflect.KClass

abstract class BasicRecyclerAdapter : BaseRecyclerAdapter() {

    private var holders = mutableMapOf<Int, Pair<Int, (View) -> RecyclerView.ViewHolder>>()

    fun <ItemT : Any, HolderT : RecyclerView.ViewHolder> addViewHolder(itemClass: KClass<ItemT>, @LayoutRes layoutRes: Int, factory: (View) -> HolderT) {
        holders[itemClass.hashCode()] = layoutRes to factory
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            holders[viewType]
                    ?.let { (layoutRes, factory) -> factory(parent.inflate(layoutRes)) }
                    ?: throw ViewHolderNotRegisteredException(getItemClassName(viewType))
}