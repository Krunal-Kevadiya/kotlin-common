package com.kotlinlibrary.statusbaralert

import android.animation.Animator
import android.app.Activity
import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator

class StatusBarAlert {
    class Builder(private var context: Activity) {
        private var text: Int = 0
        private var stringText: String = ""
        private var alertColor: Int = 0
        private var showProgress: Boolean = false
        private var showTextAnimation: Boolean = false
        private var duration: Long = 2000
        private var autoHide: Boolean = true
        private var typeFace: Typeface? = null
        private var addedStack: Boolean = false

        fun withAlertColor(alertColor: Int): Builder {
            this.alertColor = alertColor
            return this
        }

        fun withText(text: Int): Builder {
            this.text = text
            return this
        }

        fun withText(text: String): Builder {
            this.stringText = text
            return this
        }

        fun showProgress(showProgress: Boolean): Builder {
            this.showProgress = showProgress
            return this
        }

        fun showTextAnimation(showTextAnimation: Boolean): Builder {
            this.showTextAnimation = showTextAnimation
            return this
        }

        fun autoHide(autoHide: Boolean): Builder {
            this.autoHide = autoHide
            return this
        }

        fun withDuration(millis: Long): Builder {
            this.duration = millis
            return this
        }

        fun withTypeface(typeface: Typeface): Builder {
            this.typeFace = typeface
            return this
        }

        fun withAddedStack(addedStack: Boolean): Builder {
            this.addedStack = addedStack
            return this
        }

        fun build(): StatusBarAlertView? =
            addStatusBarTextAndProgress(
                context,
                text,
                stringText,
                alertColor,
                showProgress,
                showTextAnimation,
                typeFace,
                autoHide,
                duration,
                addedStack
            )
    }

    companion object {
        @JvmField
        val allAlerts: MutableMap<String, MutableList<StatusBarAlertView>?> = mutableMapOf()

        internal fun addStatusBarTextAndProgress(
            any: Activity,
            text: Int?,
            stringText:
            String?,
            alertColor: Int,
            showProgress: Boolean,
            showTextAnimation: Boolean,
            typeFace: Typeface?,
            autoHide: Boolean,
            duration: Long,
            addedStack: Boolean
        ): StatusBarAlertView? {
            hide(any, null)
            val statusBarAlert = StatusBarAlertView(any, alertColor, stringText, text, typeFace, showProgress, showTextAnimation, autoHide, duration)

            if (addedStack) {
                if (allAlerts[any.componentName.className] == null) {
                    allAlerts[any.componentName.className] = mutableListOf()
                }

                allAlerts[any.componentName.className]?.add(statusBarAlert)
            }

            return statusBarAlert
        }

        fun hide(any: Activity, onHidden: Runnable?) {
            if (allAlerts[any.componentName.className] == null || allAlerts[any.componentName.className]?.size == 0) {
                onHidden?.run()
            } else {
                allAlerts[any.componentName.className]?.forEach {
                    hideInternal(any, it, onHidden)
                }
                allAlerts[any.componentName.className]?.clear()
            }
        }

        private fun hideInternal(any: Activity, it: StatusBarAlertView, onHidden: Runnable?) {
            if (it.parent != null) {
                any.window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    any.window.statusBarColor = it.statusBarColorOringinal
                    if (it.hasOriginalStatusBarTranslucent) {
                        any.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    }
                }
                val decor = any.window.decorView as ViewGroup

                it.animate()
                    ?.translationY(-any.getStatusBarHeight().toFloat())
                    ?.setDuration(150)
                    ?.setStartDelay(500)
                    ?.setInterpolator(AccelerateInterpolator())
                    ?.setListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {}
                        override fun onAnimationEnd(animation: Animator?) {
                            decor.removeView(it)
                            onHidden?.run()
                        }

                        override fun onAnimationStart(animation: Animator?) {}
                        override fun onAnimationCancel(animation: Animator?) {}
                    })
                    ?.start()
            }
        }
    }
}
