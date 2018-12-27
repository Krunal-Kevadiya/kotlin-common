package com.kotlinlibrary.recycleradapter.dsladapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.dsladapter.simple.SingleAdapter
import com.kotlinlibrary.recycleradapter.dsladapter.simple.SingleAdapterConfiguration
import com.kotlinlibrary.recycleradapter.dsladapter.typed.MultiTypedAdapter
import com.kotlinlibrary.recycleradapter.dsladapter.typed.MultiTypedAdapterConfiguration

fun <T> RecyclerView.setUp(block: SingleAdapterConfiguration<T>.() -> Unit): SingleAdapter<T> {
    val configuration = SingleAdapterConfiguration<T>().apply(block)
    return SingleAdapter(configuration).apply {
        layoutManager = configuration.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}

fun RecyclerView.setUp(block: MultiTypedAdapterConfiguration.() -> Unit): MultiTypedAdapter {
    val adapterDsl = MultiTypedAdapterConfiguration().apply(block)
    return MultiTypedAdapter(adapterDsl).apply {
        layoutManager = adapterDsl.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}