package com.kotlinlibrary.snackbar.anim

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context

class SnackAnim(private val compositeAnim: AnimatorSet) {

    companion object {
        @JvmStatic
        fun with(context: Context) =  SnackAnimRetriever(context)
    }

    internal fun start(listener: InternalAnimListener? = null) {
        if (listener != null) {
            val primaryAnim = compositeAnim.childAnimations[0] as ObjectAnimator

            primaryAnim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animator: Animator) {}

                override fun onAnimationEnd(animator: Animator) {
                    listener.onStop()

                    primaryAnim.removeAllListeners()
                    primaryAnim.removeAllUpdateListeners()
                }

                override fun onAnimationCancel(animator: Animator) {}

                override fun onAnimationStart(animator: Animator) {
                    listener.onStart()
                }
            })

            primaryAnim.addUpdateListener {
                listener.onUpdate(it.animatedFraction)
            }
        }

        compositeAnim.start()
    }

    internal interface InternalAnimListener {
        fun onStart()
        fun onUpdate(progress: Float)
        fun onStop()
    }
}

class SnackAnimRetriever(private val context: Context) {
    fun animateBar() = SnackAnimBarBuilder(context)

    fun animateIcon() = SnackAnimIconBuilder(context)
}