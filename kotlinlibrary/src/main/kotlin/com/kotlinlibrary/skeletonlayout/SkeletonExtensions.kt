@file:JvmName("SkeletonLayoutUtils")

package com.kotlinlibrary.skeletonlayout

import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.skeletonlayout.recyclerview.SkeletonRecyclerView

private const val LIST_ITEM_COUNT_DEFAULT = 3

@JvmOverloads
fun View.createSkeleton(
    @ColorInt maskColor: Int = ContextCompat.getColor(context, SkeletonLayout.DEFAULT_MASK_COLOR),
    cornerRadius: Float = SkeletonLayout.DEFAULT_CORNER_RADIUS,
    showShimmer: Boolean = SkeletonLayout.DEFAULT_SHIMMER_SHOW,
    @ColorInt shimmerColor: Int = ContextCompat.getColor(context, SkeletonLayout.DEFAULT_SHIMMER_COLOR),
    shimmerDurationInMillis: Long = SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS
): Skeleton = SkeletonLayout(this, maskColor, cornerRadius, showShimmer, shimmerColor, shimmerDurationInMillis)

@JvmOverloads
fun RecyclerView.applySkeleton(
    @LayoutRes listItemLayoutResId: Int,
    itemCount: Int = LIST_ITEM_COUNT_DEFAULT,
    @ColorInt maskColor: Int = ContextCompat.getColor(context, SkeletonLayout.DEFAULT_MASK_COLOR),
    cornerRadius: Float = SkeletonLayout.DEFAULT_CORNER_RADIUS,
    showShimmer: Boolean = SkeletonLayout.DEFAULT_SHIMMER_SHOW,
    @ColorInt shimmerColor: Int = ContextCompat.getColor(context, SkeletonLayout.DEFAULT_SHIMMER_COLOR),
    shimmerDurationInMillis: Long = SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS
): Skeleton = SkeletonRecyclerView(this, listItemLayoutResId, itemCount, maskColor, cornerRadius, showShimmer, shimmerColor, shimmerDurationInMillis)