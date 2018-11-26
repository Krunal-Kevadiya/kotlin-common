package com.kotlinlibrary.snackbar

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.HapticFeedbackConstants.VIRTUAL_KEY
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import com.kotlinlibrary.snackbar.Snackbar.Companion.DURATION_INDEFINITE
import com.kotlinlibrary.snackbar.Snackbar.DismissEvent
import com.kotlinlibrary.snackbar.Snackbar.DismissEvent.*
import com.kotlinlibrary.snackbar.Snackbar.Vibration.DISMISS
import com.kotlinlibrary.snackbar.SwipeDismissTouchListener.DismissCallbacks
import com.kotlinlibrary.snackbar.anim.SnackAnim
import com.kotlinlibrary.snackbar.anim.SnackAnimBarBuilder
import com.kotlinlibrary.snackbar.anim.SnackAnimIconBuilder
import com.kotlinlibrary.snackbar.util.NavigationBarPosition.*
import com.kotlinlibrary.snackbar.util.afterMeasured
import com.kotlinlibrary.snackbar.util.getNavigationBarPosition
import com.kotlinlibrary.snackbar.util.getNavigationBarSizeInPx
import com.kotlinlibrary.snackbar.util.getRootView

internal class SnackbarContainerView(context: Context)
    : RelativeLayout(context), DismissCallbacks {

    internal lateinit var parentSnackbar: Snackbar

    private lateinit var snackbarView: SnackbarView

    private lateinit var enterAnimBuilder: SnackAnimBarBuilder
    private lateinit var exitAnimBuilder: SnackAnimBarBuilder
    private lateinit var vibrationTargets: List<Snackbar.Vibration>

    private var onBarShowListener: Snackbar.OnBarShowListener? = null
    private var onBarDismissListener: Snackbar.OnBarDismissListener? = null
    private var onTapOutsideListener: Snackbar.OnTapListener? = null
    private var overlayColor: Int? = null
    private var iconAnimBuilder: SnackAnimIconBuilder? = null

    private var duration = DURATION_INDEFINITE
    private var isBarShowing = false
    private var isBarShown = false
    private var isBarDismissing = false
    private var barDismissOnTapOutside: Boolean = false
    private var showOverlay: Boolean = false
    private var overlayBlockable: Boolean = false

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_DOWN -> {
                val rect = Rect()
                snackbarView.getHitRect(rect)

                // Checks if the tap was outside the bar
                if (!rect.contains(event.x.toInt(), event.y.toInt())) {
                    onTapOutsideListener?.onTap(parentSnackbar)

                    if (barDismissOnTapOutside) {
                        dismissInternal(TAP_OUTSIDE)
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onSwipe(isSwiping: Boolean) {
        isBarDismissing = isSwiping
        if (isSwiping) {
            onBarDismissListener?.onDismissing(parentSnackbar, true)
        }
    }

    override fun onDismiss(view: View) {
        (parent as? ViewGroup)?.removeView(this@SnackbarContainerView)
        isBarShown = false

        snackbarView.stopIconAnimation()

        if (vibrationTargets.contains(DISMISS)) {
            performHapticFeedback(VIRTUAL_KEY)
        }

        onBarDismissListener?.onDismissed(parentSnackbar, SWIPE)
    }

    internal fun attach(snackbarView: SnackbarView) {
        this.snackbarView = snackbarView
    }

    internal fun construct() {
        isHapticFeedbackEnabled = true

        if (showOverlay) {
            setBackgroundColor(overlayColor!!)

            if (overlayBlockable) {
                isClickable = true
                isFocusable = true
            }
        }

        addView(snackbarView)
    }

    internal fun addParent(snackbar: Snackbar) {
        this.parentSnackbar = snackbar
    }

    internal fun adjustOrientation(activity: Activity) {
        val snackbarContainerViewLp = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)

        val navigationBarPosition = activity.getNavigationBarPosition()
        val navigationBarSize = activity.getNavigationBarSizeInPx()

        when (navigationBarPosition) {
            LEFT -> snackbarContainerViewLp.leftMargin = navigationBarSize
            RIGHT -> snackbarContainerViewLp.rightMargin = navigationBarSize
            BOTTOM -> snackbarContainerViewLp.bottomMargin = navigationBarSize
        }

        layoutParams = snackbarContainerViewLp
    }


    internal fun show(activity: Activity) {
        if (isBarShowing || isBarShown) return

        val activityRootView = activity.getRootView() ?: return

        // Only add the withView to the parent once
        if (this.parent == null) activityRootView.addView(this)

        activityRootView.afterMeasured {
            val enterAnim = enterAnimBuilder.withView(snackbarView).build()
            enterAnim.start(object : SnackAnim.InternalAnimListener {
                override fun onStart() {
                    isBarShowing = true
                    onBarShowListener?.onShowing(parentSnackbar)
                }

                override fun onUpdate(progress: Float) {
                    onBarShowListener?.onShowProgress(parentSnackbar, progress)
                }

                override fun onStop() {
                    isBarShowing = false
                    isBarShown = true

                    snackbarView.startIconAnimation(iconAnimBuilder)

                    if (vibrationTargets.contains(Snackbar.Vibration.SHOW)) {
                        performHapticFeedback(VIRTUAL_KEY)
                    }

                    onBarShowListener?.onShown(parentSnackbar)
                }
            })

            handleDismiss()
        }
    }

    internal fun dismiss() {
        dismissInternal(MANUAL)
    }

    internal fun isBarShowing() = isBarShowing

    internal fun isBarShown() = isBarShown

    internal fun setDuration(duration: Long) {
        this.duration = duration
    }

    internal fun setBarShowListener(listener: Snackbar.OnBarShowListener?) {
        this.onBarShowListener = listener
    }

    internal fun setBarDismissListener(listener: Snackbar.OnBarDismissListener?) {
        this.onBarDismissListener = listener
    }

    internal fun setBarDismissOnTapOutside(dismiss: Boolean) {
        this.barDismissOnTapOutside = dismiss
    }

    internal fun setOnTapOutsideListener(listener: Snackbar.OnTapListener?) {
        this.onTapOutsideListener = listener
    }

    internal fun setOverlay(overlay: Boolean) {
        this.showOverlay = overlay
    }

    internal fun setOverlayColor(color: Int) {
        this.overlayColor = color
    }

    internal fun setOverlayBlockable(blockable: Boolean) {
        this.overlayBlockable = blockable
    }

    internal fun setEnterAnim(builder: SnackAnimBarBuilder) {
        this.enterAnimBuilder = builder
    }

    internal fun setExitAnim(builder: SnackAnimBarBuilder) {
        this.exitAnimBuilder = builder
    }

    internal fun enableSwipeToDismiss(enable: Boolean) {
        this.snackbarView.enableSwipeToDismiss(enable, this)
    }

    internal fun setVibrationTargets(targets: List<Snackbar.Vibration>) {
        this.vibrationTargets = targets
    }

    internal fun setIconAnim(builder: SnackAnimIconBuilder?) {
        this.iconAnimBuilder = builder
    }

    private fun handleDismiss() {
        if (duration != DURATION_INDEFINITE) {
            postDelayed({ dismissInternal(TIMEOUT) }, duration)
        }
    }

    private fun dismissInternal(event: DismissEvent) {
        if (isBarDismissing || isBarShowing || !isBarShown) {
            return
        }

        val exitAnim = exitAnimBuilder.withView(snackbarView).build()
        exitAnim.start(object : SnackAnim.InternalAnimListener {
            override fun onStart() {
                isBarDismissing = true
                onBarDismissListener?.onDismissing(parentSnackbar, false)
            }

            override fun onUpdate(progress: Float) {
                onBarDismissListener?.onDismissProgress(parentSnackbar, progress)
            }

            override fun onStop() {
                isBarDismissing = false
                isBarShown = false

                if (vibrationTargets.contains(DISMISS)) {
                    performHapticFeedback(VIRTUAL_KEY)
                }

                onBarDismissListener?.onDismissed(parentSnackbar, event)

                post { (parent as? ViewGroup)?.removeView(this@SnackbarContainerView) }
            }
        })
    }
}
