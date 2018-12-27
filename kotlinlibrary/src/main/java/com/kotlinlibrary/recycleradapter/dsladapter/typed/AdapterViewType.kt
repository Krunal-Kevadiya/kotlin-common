package com.kotlinlibrary.recycleradapter.dsladapter.typed

class AdapterViewType<T>(hashCode: Int, block: AdapterViewTypeConfiguration<T>.() -> Unit) {
    internal val configuration = AdapterViewTypeConfiguration<T>().apply(block)
    val viewType: Int = hashCode
}