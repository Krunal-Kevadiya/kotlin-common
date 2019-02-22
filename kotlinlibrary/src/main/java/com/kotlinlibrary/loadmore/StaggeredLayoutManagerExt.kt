package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun StaggeredGridLayoutManager.findLastCompletelyVisibleItemPosition(): Int {
    val lastPositions = IntArray(spanCount)
    this.findLastVisibleItemPositions(lastPositions)
    return findMax(lastPositions)
}

fun StaggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(): Int {
    val firstPositions = IntArray(spanCount)
    this.findFirstCompletelyVisibleItemPositions(firstPositions)
    return findMin(firstPositions)
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

private fun findMin(firstPositions: IntArray): Int {
    var min = firstPositions[0]

    for (value in firstPositions) {
        if (value < min) {
            min = value
        }
    }
    return min
}
