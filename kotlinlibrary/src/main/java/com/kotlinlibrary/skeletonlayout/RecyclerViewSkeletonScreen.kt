package com.kotlinlibrary.skeletonlayout

import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.R

class RecyclerViewSkeletonScreen private constructor(builder: Builder) : SkeletonScreen {
    private val mRecyclerView: RecyclerView
    private val mActualAdapter: RecyclerView.Adapter<*>?
    private val mSkeletonAdapter: SkeletonAdapter
    private val mRecyclerViewFrozen: Boolean

    init {
        mRecyclerView = builder.mRecyclerView
        mActualAdapter = builder.mActualAdapter
        mSkeletonAdapter = SkeletonAdapter()
        mSkeletonAdapter.itemCount = builder.mItemCount
        mSkeletonAdapter.setLayoutReference(builder.mItemResID)
        mSkeletonAdapter.setArrayOfLayoutReferences(builder.mItemsResIDArray)
        mSkeletonAdapter.shimmer(builder.mShimmer)
        mSkeletonAdapter.setShimmerColor(builder.mShimmerColor)
        mSkeletonAdapter.setShimmerAngle(builder.mShimmerAngle)
        mSkeletonAdapter.setShimmerDuration(builder.mShimmerDuration)
        mRecyclerViewFrozen = builder.mFrozen
    }

    override fun show() {
        mRecyclerView.adapter = mSkeletonAdapter
        if (!mRecyclerView.isComputingLayout && mRecyclerViewFrozen) {
            mRecyclerView.isLayoutFrozen = true
        }
    }

    override fun hide() {
        mRecyclerView.adapter = mActualAdapter
    }

    class Builder(val mRecyclerView: RecyclerView) {
        var mActualAdapter: RecyclerView.Adapter<*>? = null
        var mShimmer = true
        var mItemCount = 10
        var mItemResID = R.layout.layout_default_item_skeleton
        var mItemsResIDArray: IntArray? = null
        var mShimmerColor: Int = 0
        var mShimmerDuration = 1000
        var mShimmerAngle = 20
        var mFrozen = true

        init {
            this.mShimmerColor = ContextCompat.getColor(mRecyclerView.context, R.color.shimmer_color)
        }

        /**
         * @param adapter the target recyclerView actual adapter
         */
        fun adapter(adapter: RecyclerView.Adapter<*>): Builder {
            this.mActualAdapter = adapter
            return this
        }

        /**
         * @param itemCount the child item count in recyclerView
         */
        fun count(itemCount: Int): Builder {
            this.mItemCount = itemCount
            return this
        }

        /**
         * @param shimmer whether show shimmer animation
         */
        fun shimmer(shimmer: Boolean): Builder {
            this.mShimmer = shimmer
            return this
        }

        /**
         * the duration of the animation , the time it will take for the highlight to move from one end of the layout
         * to the other.
         *
         * @param shimmerDuration Duration of the shimmer animation, in milliseconds
         */
        fun duration(shimmerDuration: Int): Builder {
            this.mShimmerDuration = shimmerDuration
            return this
        }

        /**
         * @param shimmerColor the shimmer color
         */
        fun color(@ColorRes shimmerColor: Int): Builder {
            this.mShimmerColor = ContextCompat.getColor(mRecyclerView.context, shimmerColor)
            return this
        }

        /**
         * @param shimmerAngle the angle of the shimmer effect in clockwise direction in degrees.
         */
        fun angle(@IntRange(from = 0, to = 30) shimmerAngle: Int): Builder {
            this.mShimmerAngle = shimmerAngle
            return this
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        fun load(@LayoutRes skeletonLayoutResID: Int): Builder {
            this.mItemResID = skeletonLayoutResID
            return this
        }

        /**
         * @param skeletonLayoutResIDs the loading array of skeleton layoutResID
         */
        fun loadArrayOfLayouts(@ArrayRes skeletonLayoutResIDs: IntArray): Builder {
            this.mItemsResIDArray = skeletonLayoutResIDs
            return this
        }

        /**
         * @param frozen whether frozen recyclerView during skeleton showing
         * @return
         */
        fun frozen(frozen: Boolean): Builder {
            this.mFrozen = frozen
            return this
        }

        fun create(): RecyclerViewSkeletonScreen {
            return RecyclerViewSkeletonScreen(this)
        }

        fun show(): RecyclerViewSkeletonScreen {
            val recyclerViewSkeleton = RecyclerViewSkeletonScreen(this)
            recyclerViewSkeleton.show()
            return recyclerViewSkeleton
        }
    }
}
