package com.kotlinlibrary.recycleradapter.typed

class AdapterViewType<T>(hashCode: Int, block: AdapterViewTypeConfiguration<T>.() -> Unit) {
    internal val configuration = AdapterViewTypeConfiguration<T>().apply(block)
    val viewType: Int = hashCode
}