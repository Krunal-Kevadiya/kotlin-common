package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun StaggeredGridLayoutManager.findLastCompletelyVisibleItemPosition(): Int {
    val lastPositions = IntArray(spanCount)
    this.findLastVisibleItemPositions(lastPositions)
    return findMax(lastPositions)
}

private fun findMax(lastPositions: IntArray): Int {
    var max = lastPositions[0]

    for (value in lastPositions) {
        if (value > max) {
            max = value
        }
    }
    return max
}
