package com.kotlinlibrary.recycleradapter.adapter

import com.kotlinlibrary.recycleradapter.adapter.base.BasicRecyclerAdapter

class SimpleBasicRecyclerAdapter : BasicRecyclerAdapter() {

    var items: List<Any>
        get() = displayedItems
        set(value) {
            displayedItems = value
            notifyDataSetChanged()
        }
}