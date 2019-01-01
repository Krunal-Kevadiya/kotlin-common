package com.kotlinlibrary.recycleradapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.simple.SingleAdapter
import com.kotlinlibrary.recycleradapter.simple.SingleAdapterConfiguration
import com.kotlinlibrary.recycleradapter.simple.SingleBindingAdapter
import com.kotlinlibrary.recycleradapter.typed.MultiTypedAdapter
import com.kotlinlibrary.recycleradapter.typed.MultiTypedAdapterConfiguration
import com.kotlinlibrary.recycleradapter.typed.MultiTypedBindingAdapter

fun <T> RecyclerView.setUp(block: SingleAdapterConfiguration<T>.() -> Unit): SingleAdapter<T> {
    val configuration = SingleAdapterConfiguration<T>().apply(block)
    return SingleAdapter(configuration).apply {
        layoutManager = configuration.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}

fun <T> RecyclerView.setUpBinding(block: SingleAdapterConfiguration<T>.() -> Unit): SingleBindingAdapter<T> {
    val configuration = SingleAdapterConfiguration<T>().apply(block)
    return SingleBindingAdapter(configuration).apply {
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

fun RecyclerView.setUpBinding(block: MultiTypedAdapterConfiguration.() -> Unit): MultiTypedBindingAdapter {
    val adapterDsl = MultiTypedAdapterConfiguration().apply(block)
    return MultiTypedBindingAdapter(adapterDsl).apply {
        layoutManager = adapterDsl.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}