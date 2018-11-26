package com.kotlinlibrary.skeletonlayout


interface Skeleton {
    var maskColor: Int
    var shimmerColor: Int
    var showShimmer: Boolean
    var maskCornerRadius: Float
    var shimmerDurationInMillis: Long

    fun showOriginal()

    fun showSkeleton()

    fun isSkeleton(): Boolean
}