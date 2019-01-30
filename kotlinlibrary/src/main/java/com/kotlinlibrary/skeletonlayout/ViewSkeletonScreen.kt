package com.kotlinlibrary.skeletonlayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.kotlinlibrary.R

class ViewSkeletonScreen private constructor(builder: Builder) : SkeletonScreen {
    private val mViewReplacer: ViewReplacer
    private val mActualView: View
    private val mSkeletonResID: Int
    private val mShimmerColor: Int
    private val mShimmer: Boolean
    private val mShimmerDuration: Int
    private val mShimmerAngle: Int

    init {
        mActualView = builder.mView
        mSkeletonResID = builder.mSkeletonLayoutResID
        mShimmer = builder.mShimmer
        mShimmerDuration = builder.mShimmerDuration
        mShimmerAngle = builder.mShimmerAngle
        mShimmerColor = builder.mShimmerColor
        mViewReplacer = ViewReplacer(builder.mView)
    }

    private fun generateShimmerContainerLayout(parentView: ViewGroup): ShimmerLayout {
        val shimmerLayout = LayoutInflater.from(mActualView.context).inflate(
            R.layout.layout_shimmer, parentView, false) as ShimmerLayout
        shimmerLayout.setShimmerColor(mShimmerColor)
        shimmerLayout.setShimmerAngle(mShimmerAngle)
        shimmerLayout.setShimmerAnimationDuration(mShimmerDuration)
        val innerView = LayoutInflater.from(mActualView.context).inflate(mSkeletonResID, shimmerLayout, false)
        val lp = innerView.layoutParams
        if (lp != null) {
            shimmerLayout.layoutParams = lp
        }
        shimmerLayout.addView(innerView)
        shimmerLayout.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                shimmerLayout.startShimmerAnimation()
            }

            override fun onViewDetachedFromWindow(v: View) {
                shimmerLayout.stopShimmerAnimation()
            }
        })
        shimmerLayout.startShimmerAnimation()
        return shimmerLayout
    }

    private fun generateSkeletonLoadingView(): View? {
        val viewParent = mActualView.parent ?: return null
        val parentView = viewParent as ViewGroup
        return if (mShimmer) {
            generateShimmerContainerLayout(parentView)
        } else LayoutInflater.from(mActualView.context).inflate(mSkeletonResID, parentView, false)
    }

    override fun show() {
        val skeletonLoadingView = generateSkeletonLoadingView()
        if (skeletonLoadingView != null) {
            mViewReplacer.replace(skeletonLoadingView)
        }
    }

    override fun hide() {
        if (mViewReplacer.targetView is ShimmerLayout) {
            (mViewReplacer.targetView as ShimmerLayout).stopShimmerAnimation()
        }
        mViewReplacer.restore()
    }

    class Builder(val mView: View) {
        var mSkeletonLayoutResID: Int = 0
        var mShimmer = true
        var mShimmerColor: Int = 0
        var mShimmerDuration = 1000
        var mShimmerAngle = 20

        init {
            this.mShimmerColor = ContextCompat.getColor(mView.context, R.color.shimmer_color)
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        fun load(@LayoutRes skeletonLayoutResID: Int): Builder {
            this.mSkeletonLayoutResID = skeletonLayoutResID
            return this
        }

        /**
         * @param shimmerColor the shimmer color
         */
        fun color(@ColorRes shimmerColor: Int): Builder {
            this.mShimmerColor = ContextCompat.getColor(mView.context, shimmerColor)
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
         * @param shimmerAngle the angle of the shimmer effect in clockwise direction in degrees.
         */
        fun angle(@IntRange(from = 0, to = 30) shimmerAngle: Int): Builder {
            this.mShimmerAngle = shimmerAngle
            return this
        }

        fun show(): ViewSkeletonScreen {
            val skeletonScreen = ViewSkeletonScreen(this)
            skeletonScreen.show()
            return skeletonScreen
        }

    }
}
