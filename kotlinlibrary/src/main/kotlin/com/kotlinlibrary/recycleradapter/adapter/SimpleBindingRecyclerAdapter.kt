package com.kotlinlibrary.recycleradapter.adapter

import com.kotlinlibrary.recycleradapter.adapter.base.BindingRecyclerAdapter

class SimpleBindingRecyclerAdapter : BindingRecyclerAdapter() {

    var items: List<Any>
        get() = displayedItems
        set(value) {
            displayedItems = value
            notifyDataSetChanged()
        }
}