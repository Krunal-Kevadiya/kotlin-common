package com.kotlinlibrary.snackbar.anim

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import androidx.annotation.InterpolatorRes
import com.kotlinlibrary.snackbar.Snackbar
import com.kotlinlibrary.snackbar.Snackbar.Gravity.BOTTOM
import com.kotlinlibrary.snackbar.Snackbar.Gravity.TOP
import com.kotlinlibrary.snackbar.anim.SnackAnimBarBuilder.Direction.LEFT
import com.kotlinlibrary.snackbar.anim.SnackAnimBarBuilder.Direction.RIGHT
import com.kotlinlibrary.snackbar.anim.SnackAnimBarBuilder.Type.ENTER
import com.kotlinlibrary.snackbar.anim.SnackAnimBarBuilder.Type.EXIT

class SnackAnimBarBuilder(context: Context) : BaseSnackAnimBuilder(context) {

    private var type: Type? = null
    private var gravity: Snackbar.Gravity? = null
    private var direction: Direction? = null

    override fun duration(millis: Long) = apply { super.duration(millis) }

    override fun accelerate() = apply { super.accelerate() }

    override fun decelerate() = apply { super.decelerate() }

    override fun accelerateDecelerate() = apply { super.accelerateDecelerate() }

    override fun interpolator(interpolator: Interpolator) = apply { super.interpolator(interpolator) }

    override fun interpolator(@InterpolatorRes id: Int) = apply { super.interpolator(id) }

    override fun alpha() = apply { super.alpha() }

    fun slideFromLeft() = apply {
        this.direction = LEFT
    }

    fun slideFromRight() = apply {
        this.direction = RIGHT
    }

    fun overshoot() = apply {
        this.interpolator = OvershootInterpolator()
    }

    fun anticipateOvershoot() = apply {
        this.interpolator = AnticipateInterpolator()
    }

    override fun withView(view: View) = apply { super.withView(view) }

    internal fun enter() = apply {
        this.type = ENTER
    }

    internal fun exit() = apply {
        this.type = EXIT
    }

    internal fun fromTop() = apply {
        this.gravity = TOP
    }

    internal fun fromBottom() = apply {
        this.gravity = BOTTOM
    }

    internal fun build(): SnackAnim {
        requireNotNull(view) { "Target view can not be null" }
        val compositeAnim = AnimatorSet()
        val animators = linkedSetOf<Animator>()

        val translationAnim = ObjectAnimator()
        // Slide from left/right animation is not specified, default top/bottom
        // animation is applied
        if (direction == null) {
            translationAnim.setPropertyName("translationY")

            when (type!!) {
                ENTER -> when (gravity!!) {
                    TOP -> translationAnim.setFloatValues(-view!!.height.toFloat(), 0f)
                    BOTTOM -> translationAnim.setFloatValues(view!!.height.toFloat(), 0f)
                }
                EXIT -> when (gravity!!) {
                    TOP -> translationAnim.setFloatValues(0f, -view!!.height.toFloat())
                    BOTTOM -> translationAnim.setFloatValues(0f, view!!.height.toFloat())
                }
            }
        } else {
            translationAnim.setPropertyName("translationX")

            when (type!!) {
                ENTER -> when (direction!!) {
                    LEFT -> translationAnim.setFloatValues(-view!!.width.toFloat(), 0f)
                    RIGHT -> translationAnim.setFloatValues(view!!.width.toFloat(), 0f)
                }
                EXIT -> when (direction!!) {
                    LEFT -> translationAnim.setFloatValues(0f, -view!!.width.toFloat())
                    RIGHT -> translationAnim.setFloatValues(0f, view!!.width.toFloat())
                }
            }
        }

        translationAnim.target = view
        animators.add(translationAnim)

        if (alpha) {
            val alphaAnim = ObjectAnimator()
            alphaAnim.setPropertyName("alpha")
            alphaAnim.target = view

            when (type!!) {
                ENTER -> alphaAnim.setFloatValues(defaultAlphaStart, defaultAlphaEnd)
                EXIT -> alphaAnim.setFloatValues(defaultAlphaEnd, defaultAlphaStart)
            }
            animators.add(alphaAnim)
        }

        compositeAnim.playTogether(animators)
        compositeAnim.duration = duration
        compositeAnim.interpolator = interpolator

        return SnackAnim(compositeAnim)
    }

    enum class Type { ENTER, EXIT }
    enum class Direction { LEFT, RIGHT }
}