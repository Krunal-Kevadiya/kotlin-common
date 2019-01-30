package com.kotlinlibrary.skeletonlayout

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView

class SkeletonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItemCount: Int = 0
    private var mLayoutReference: Int = 0
    private var mLayoutArrayReferences: IntArray? = null
    private var mColor: Int = 0
    private var mShimmer: Boolean = false
    private var mShimmerDuration: Int = 0
    private var mShimmerAngle: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (doesArrayOfLayoutsExist()) {
            mLayoutReference = viewType
        }
        return if (mShimmer) {
            ShimmerViewHolder(inflater, parent, mLayoutReference)
        } else {
            object : RecyclerView.ViewHolder(inflater.inflate(mLayoutReference, parent, false)) {
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mShimmer) {
            val layout = holder.itemView as ShimmerLayout
            layout.setShimmerAnimationDuration(mShimmerDuration)
            layout.setShimmerAngle(mShimmerAngle)
            layout.setShimmerColor(mColor)
            layout.startShimmerAnimation()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (doesArrayOfLayoutsExist()) {
            getCorrectLayoutItem(position)
        } else super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItemCount
    }

    fun setLayoutReference(layoutReference: Int) {
        this.mLayoutReference = layoutReference
    }

    fun setArrayOfLayoutReferences(layoutReferences: IntArray?) {
        this.mLayoutArrayReferences = layoutReferences
    }

    fun setItemCount(itemCount: Int) {
        this.mItemCount = itemCount
    }

    fun setShimmerColor(color: Int) {
        this.mColor = color
    }

    fun shimmer(shimmer: Boolean) {
        this.mShimmer = shimmer
    }

    fun setShimmerDuration(shimmerDuration: Int) {
        this.mShimmerDuration = shimmerDuration
    }

    fun setShimmerAngle(@IntRange(from = 0, to = 30) shimmerAngle: Int) {
        this.mShimmerAngle = shimmerAngle
    }

    fun getCorrectLayoutItem(position: Int): Int {
        return if (doesArrayOfLayoutsExist()) {
            mLayoutArrayReferences!![position % mLayoutArrayReferences!!.size]
        } else mLayoutReference
    }

    private fun doesArrayOfLayoutsExist(): Boolean {
        return mLayoutArrayReferences != null && mLayoutArrayReferences!!.isNotEmpty()
    }
}
