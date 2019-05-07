package com.kotlinlibrary.statusbaralert

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class StatusBarAlertView(
    private val activity: Activity,
    alertColor: Int,
    stringText: String?,
    text: Int?,
    typeface: Typeface?,
    private val showProgress: Boolean,
    private val showTextAnimation: Boolean,
    autoHide: Boolean,
    private val autoHideDuration: Long
) : LinearLayout(activity, null, 0) {
    var statusBarColorOringinal: Int = 0
    var hasOriginalStatusBarTranslucent: Boolean = false
    private var textView: TextView? = null
    private var progressBar: ProgressBar? = null
    private var autoHideRunnable: Runnable? = null
    private var statusBarColor: Int = 0

    init {
        statusBarColor = activity.window.statusBarColor
        this.observeLifecycle(activity)
        this.buildUI(activity, alertColor, stringText, text, typeface, autoHide, autoHideDuration)
    }

    private fun observeLifecycle(activity: Context) {
        if (activity is AppCompatActivity) {
            activity.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun destroy() {
                    StatusBarAlert.hide(activity, Runnable {})
                    activity.lifecycle.removeObserver(this)
                }
            })
        }
    }

    private fun buildUI(
        activity: Activity,
        alertColor: Int,
        stringText: String?,
        text: Int?,
        typeFace: Typeface?,
        autoHide: Boolean,
        autoHideDuration: Long
    ) {
        val decor = (activity as? Activity)!!.window.decorView as ViewGroup

        this.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, activity.getStatusBarHeight())
        this.gravity = Gravity.CENTER_HORIZONTAL
        if (alertColor > 0) {
            setBackgroundColor(ContextCompat.getColor(activity, alertColor))
        }
        val ll2 = LinearLayout(activity)
        ll2.orientation = HORIZONTAL
        ll2.gravity = Gravity.CENTER_VERTICAL
        ll2.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, activity.getStatusBarHeight())

        textView = TextView(activity)
        textView?.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, activity.getStatusBarHeight())
        textView?.textSize = 11f
        textView?.setTextColor(Color.WHITE)
        textView?.gravity = Gravity.CENTER
        if (text != null) {
            textView?.text = if (text != 0) activity.resources.getString(text) + " " else if (stringText != "") "$stringText " else ""
        }
        textView?.includeFontPadding = false
        typeFace?.let { textView?.typeface = it }
        ll2.addView(textView)

        progressBar = ProgressBar(activity)
        progressBar?.isIndeterminate = true
        progressBar?.indeterminateDrawable?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        progressBar?.layoutParams = ViewGroup.LayoutParams(activity.convertDpToPixel(11f), activity.convertDpToPixel(11f))
        progressBar?.visibility = View.GONE
        ll2.addView(progressBar)

        addView(ll2)
        val decorView = activity.window.decorView.rootView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE

        decorView.setOnSystemUiVisibilityChangeListener {
            this.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        }

        hasOriginalStatusBarTranslucent = activity.isTranslucentStatusBar()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColorOringinal = activity.window.statusBarColor
            activity.window.statusBarColor = Color.TRANSPARENT
        }

        decor.addView(this)

        ll2.translationY = -activity.getStatusBarHeight().toFloat()

        ll2.animate()!!
            .translationY(0f)
            .setDuration(150)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        if (autoHide) {
            autoHideRunnable = Runnable { hideIndeterminateProgress(this) }
            postDelayed(autoHideRunnable, autoHideDuration)
        }
    }

    override fun onDetachedFromWindow() {
        autoHideRunnable?.let { removeCallbacks(it) }
        autoHideRunnable = null
        (context as Activity).window.decorView.setOnSystemUiVisibilityChangeListener(null)
        super.onDetachedFromWindow()
    }

    @SuppressLint("SetTextI18n")
    fun updateText(text: String) {
        textView?.text = "$text "
    }

    @SuppressLint("SetTextI18n")
    fun updateText(text: Int) {
        textView?.text = "${context.resources.getString(text)} "
    }

    fun showIndeterminateProgress() {
        if (showTextAnimation) {
            textView?.startProgressAnimation(autoHideDuration)
        }
        if (showProgress) {
            progressBar?.visibility = View.VISIBLE
        }
    }

    private fun hideIndeterminateProgress(it: StatusBarAlertView) {
        if (showTextAnimation) {
            textView?.stopProgressAnimation()
        }
        if (showProgress) {
            progressBar?.visibility = View.GONE
        }
        hideInternal(activity, it)
    }

    private fun hideInternal(activity: Activity, it: StatusBarAlertView) {
        if (it.parent != null) {
            activity.window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.statusBarColor = it.statusBarColorOringinal
                if (it.hasOriginalStatusBarTranslucent) {
                    activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                }
            }
            val decor = activity.window.decorView as ViewGroup

            it.animate()
                ?.translationY(-activity.getStatusBarHeight().toFloat())
                ?.setDuration(150)
                ?.setStartDelay(500)
                ?.setInterpolator(AccelerateInterpolator())
                ?.setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {
                        decor.removeView(it)
                    }

                    override fun onAnimationStart(animation: Animator?) {}
                    override fun onAnimationCancel(animation: Animator?) {}
                })
                ?.start()
        }
    }
}
