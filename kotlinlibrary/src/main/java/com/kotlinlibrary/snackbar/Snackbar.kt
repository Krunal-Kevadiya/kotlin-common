package com.kotlinlibrary.snackbar

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spanned
import android.widget.ImageView.ScaleType
import android.widget.ImageView.ScaleType.CENTER_CROP
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.kotlinlibrary.R
import com.kotlinlibrary.snackbar.Snackbar.Gravity.BOTTOM
import com.kotlinlibrary.snackbar.Snackbar.Gravity.TOP
import com.kotlinlibrary.snackbar.anim.SnackAnim
import com.kotlinlibrary.snackbar.anim.SnackAnimBarBuilder
import com.kotlinlibrary.snackbar.anim.SnackAnimIconBuilder

private const val DEFAULT_SHADOW_STRENGTH = 4
private const val DEFAULT_ICON_SCALE = 1.0f

class Snackbar private constructor(private var builder: Builder) {

    private lateinit var snackbarContainerView: SnackbarContainerView
    private lateinit var snackbarView: SnackbarView

    fun show() {
        snackbarContainerView.show(builder.activity)
    }

    fun dismiss() {
        snackbarContainerView.dismiss()
    }

    fun isShowing() = snackbarContainerView.isBarShowing()

    fun isShown() = snackbarContainerView.isBarShown()

    private fun construct() {
        snackbarContainerView = SnackbarContainerView(builder.activity)
        snackbarContainerView.adjustOrientation(builder.activity)
        snackbarContainerView.addParent(this)

        snackbarView = SnackbarView(builder.activity)
        snackbarView.init(builder.gravity, builder.castShadow, builder.shadowStrength)
        snackbarView.adjustWitPositionAndOrientation(builder.activity, builder.gravity)
        snackbarView.addParent(snackbarContainerView)

        snackbarContainerView.attach(snackbarView)

        initializeContainerDecor()
        initializeBarDecor()

        snackbarContainerView.construct()
    }

    private fun initializeContainerDecor() {
        with(snackbarContainerView) {
            setDuration(builder.duration)
            setBarShowListener(builder.onBarShowListener)
            setBarDismissListener(builder.onBarDismissListener)
            setBarDismissOnTapOutside(builder.barDismissOnTapOutside)
            setOnTapOutsideListener(builder.onTapOutsideListener)
            setOverlay(builder.overlay)
            setOverlayColor(builder.overlayColor)
            setOverlayBlockable(builder.overlayBlockable)
            setVibrationTargets(builder.vibrationTargets)
            setIconAnim(builder.iconAnimBuilder)

            setEnterAnim(builder.enterAnimBuilder!!)
            setExitAnim(builder.exitAnimBuilder!!)
            enableSwipeToDismiss(builder.enableSwipeToDismiss)
        }
    }

    private fun initializeBarDecor() {
        with(snackbarView) {
            setBarBackgroundColor(builder.backgroundColor)
            setBarBackgroundDrawable(builder.backgroundDrawable)
            setBarTapListener(builder.onBarTapListener)

            setTitle(builder.title)
            setTitleSpanned(builder.titleSpanned)
            setTitleTypeface(builder.titleTypeface)
            setTitleSizeInPx(builder.titleSizeInPx)
            setTitleSizeInSp(builder.titleSizeInSp)
            setTitleColor(builder.titleColor)
            setTitleAppearance(builder.titleAppearance)

            setMessage(builder.message)
            setMessageSpanned(builder.messageSpanned)
            setMessageTypeface(builder.messageTypeface)
            setMessageSizeInPx(builder.messageSizeInPx)
            setMessageSizeInSp(builder.messageSizeInSp)
            setMessageColor(builder.messageColor)
            setMessageAppearance(builder.messageAppearance)

            setPrimaryActionText(builder.primaryActionText)
            setPrimaryActionTextSpanned(builder.primaryActionTextSpanned)
            setPrimaryActionTextTypeface(builder.primaryActionTextTypeface)
            setPrimaryActionTextSizeInPx(builder.primaryActionTextSizeInPx)
            setPrimaryActionTextSizeInSp(builder.primaryActionTextSizeInSp)
            setPrimaryActionTextColor(builder.primaryActionTextColor)
            setPrimaryActionTextAppearance(builder.primaryActionTextAppearance)
            setPrimaryActionTapListener(builder.onPrimaryActionTapListener)

            setPositiveActionText(builder.positiveActionText)
            setPositiveActionTextSpanned(builder.positiveActionTextSpanned)
            setPositiveActionTextTypeface(builder.positiveActionTextTypeface)
            setPositiveActionTextSizeInPx(builder.positiveActionTextSizeInPx)
            setPositiveActionTextSizeInSp(builder.positiveActionTextSizeInSp)
            setPositiveActionTextColor(builder.positiveActionTextColor)
            setPositiveActionTextAppearance(builder.positiveActionTextAppearance)
            setPositiveActionTapListener(builder.onPositiveActionTapListener)

            setNegativeActionText(builder.negativeActionText)
            setNegativeActionTextSpanned(builder.negativeActionTextSpanned)
            setNegativeActionTextTypeface(builder.negativeActionTextTypeface)
            setNegativeActionTextSizeInPx(builder.negativeActionTextSizeInPx)
            setNegativeActionTextSizeInSp(builder.negativeActionTextSizeInSp)
            setNegativeActionTextColor(builder.negativeActionTextColor)
            setNegativeActionTextAppearance(builder.negativeActionTextAppearance)
            setNegativeActionTapListener(builder.onNegativeActionTapListener)

            showIcon(builder.showIcon)
            showIconScale(builder.iconScale, builder.iconScaleType)
            setIconDrawable(builder.iconDrawable)
            setIconBitmap(builder.iconBitmap)
            setIconColorFilter(builder.iconColorFilter, builder.iconColorFilterMode)

            setProgressPosition(builder.progressPosition)
            setProgressTint(builder.progressTint, builder.progressPosition)
        }
    }

    class Builder(internal var activity: Activity) {
        internal var gravity: Gravity = BOTTOM
        internal var backgroundColor: Int? = null
        internal var backgroundDrawable: Drawable? = null
        internal var duration: Long = DURATION_INDEFINITE
        internal var onBarTapListener: OnTapListener? = null
        internal var onBarShowListener: OnBarShowListener? = null
        internal var onBarDismissListener: OnBarDismissListener? = null
        internal var barDismissOnTapOutside: Boolean = false
        internal var onTapOutsideListener: OnTapListener? = null
        internal var overlay: Boolean = false
        internal var overlayColor: Int = ContextCompat.getColor(activity, R.color.modal)
        internal var overlayBlockable: Boolean = false
        internal var castShadow: Boolean = true
        internal var shadowStrength: Int = DEFAULT_SHADOW_STRENGTH
        internal var enableSwipeToDismiss: Boolean = false
        internal var vibrationTargets: List<Vibration> = emptyList()

        internal var title: String? = null
        internal var titleSpanned: Spanned? = null
        internal var titleTypeface: Typeface? = null
        internal var titleSizeInPx: Float? = null
        internal var titleSizeInSp: Float? = null
        internal var titleColor: Int? = null
        internal var titleAppearance: Int? = null

        internal var message: String? = null
        internal var messageSpanned: Spanned? = null
        internal var messageTypeface: Typeface? = null
        internal var messageSizeInPx: Float? = null
        internal var messageSizeInSp: Float? = null
        internal var messageColor: Int? = null
        internal var messageAppearance: Int? = null

        internal var primaryActionText: String? = null
        internal var primaryActionTextSpanned: Spanned? = null
        internal var primaryActionTextTypeface: Typeface? = null
        internal var primaryActionTextSizeInPx: Float? = null
        internal var primaryActionTextSizeInSp: Float? = null
        internal var primaryActionTextColor: Int? = null
        internal var primaryActionTextAppearance: Int? = null
        internal var onPrimaryActionTapListener: OnActionTapListener? = null

        internal var positiveActionText: String? = null
        internal var positiveActionTextSpanned: Spanned? = null
        internal var positiveActionTextTypeface: Typeface? = null
        internal var positiveActionTextSizeInPx: Float? = null
        internal var positiveActionTextSizeInSp: Float? = null
        internal var positiveActionTextColor: Int? = null
        internal var positiveActionTextAppearance: Int? = null
        internal var onPositiveActionTapListener: OnActionTapListener? = null

        internal var negativeActionText: String? = null
        internal var negativeActionTextSpanned: Spanned? = null
        internal var negativeActionTextTypeface: Typeface? = null
        internal var negativeActionTextSizeInPx: Float? = null
        internal var negativeActionTextSizeInSp: Float? = null
        internal var negativeActionTextColor: Int? = null
        internal var negativeActionTextAppearance: Int? = null
        internal var onNegativeActionTapListener: OnActionTapListener? = null

        internal var showIcon: Boolean = false
        internal var iconScale: Float = DEFAULT_ICON_SCALE
        internal var iconScaleType: ScaleType = CENTER_CROP
        internal var iconDrawable: Drawable? = null
        internal var iconBitmap: Bitmap? = null
        internal var iconColorFilter: Int? = null
        internal var iconColorFilterMode: PorterDuff.Mode? = null
        internal var iconAnimBuilder: SnackAnimIconBuilder? = null

        internal var progressPosition: ProgressPosition? = null
        internal var progressTint: Int? = null

        internal var enterAnimBuilder: SnackAnimBarBuilder? = null
        internal var exitAnimBuilder: SnackAnimBarBuilder? = null

        fun gravity(gravity: Gravity) = apply { this.gravity = gravity }

        fun backgroundDrawable(drawable: Drawable) = apply { this.backgroundDrawable = drawable }

        fun backgroundDrawable(@DrawableRes drawableId: Int) = apply {
            this.backgroundDrawable = ContextCompat.getDrawable(activity, drawableId)
        }

        fun backgroundColor(@ColorInt color: Int) = apply { this.backgroundColor = color }

        fun backgroundColorRes(@ColorRes colorId: Int) = apply {
            this.backgroundColor = ContextCompat.getColor(activity, colorId)
        }

        fun listenBarTaps(listener: OnTapListener) = apply {
            this.onBarTapListener = listener
        }

        fun duration(milliseconds: Long) = apply {
            require(milliseconds > 0) { "Duration can not be negative or zero" }
            this.duration = milliseconds
        }

        fun barShowListener(listener: OnBarShowListener) = apply {
            this.onBarShowListener = listener
        }

        fun barDismissListener(listener: OnBarDismissListener) = apply {
            this.onBarDismissListener = listener
        }

        fun listenOutsideTaps(listener: OnTapListener) = apply {
            this.onTapOutsideListener = listener
        }

        fun dismissOnTapOutside() = apply {
            this.barDismissOnTapOutside = true
        }

        fun showOverlay() = apply { this.overlay = true }

        fun overlayColor(@ColorInt color: Int) = apply { this.overlayColor = color }

        fun overlayColorRes(@ColorRes colorId: Int) = apply {
            this.overlayColor = ContextCompat.getColor(activity, colorId)
        }

        fun overlayBlockable() = apply {
            this.overlayBlockable = true
        }

        @JvmOverloads
        fun castShadow(shadow: Boolean = true, strength: Int = DEFAULT_SHADOW_STRENGTH) = apply {
            require(strength > 0) { "Shadow strength can not be negative or zero" }

            this.castShadow = shadow
            this.shadowStrength = strength
        }

        fun enterAnimation(builder: SnackAnimBarBuilder) = apply {
            this.enterAnimBuilder = builder
        }

        fun exitAnimation(builder: SnackAnimBarBuilder) = apply {
            this.exitAnimBuilder = builder
        }

        fun enableSwipeToDismiss() = apply {
            this.enableSwipeToDismiss = true
        }

        fun vibrateOn(vararg vibrate: Vibration) = apply {
            require(vibrate.isNotEmpty()) { "Vibration targets can not be empty" }
            this.vibrationTargets = vibrate.toList()
        }

        fun title(title: String) = apply { this.title = title }

        fun title(@StringRes titleId: Int) = apply { this.title = activity.getString(titleId) }

        fun title(title: Spanned) = apply { this.titleSpanned = title }

        fun titleTypeface(typeface: Typeface) = apply { this.titleTypeface = typeface }

        fun titleSizeInPx(size: Float) = apply { this.titleSizeInPx = size }

        fun titleSizeInSp(size: Float) = apply { this.titleSizeInSp = size }

        fun titleColor(@ColorInt color: Int) = apply { this.titleColor = color }

        fun titleColorRes(@ColorRes colorId: Int) = apply {
            this.titleColor = ContextCompat.getColor(activity, colorId)
        }

        fun titleAppearance(@StyleRes appearance: Int) = apply {
            this.titleAppearance = appearance
        }

        fun message(message: String) = apply { this.message = message }

        fun message(@StringRes messageId: Int) = apply {
            this.message = activity.getString(messageId)
        }

        fun message(message: Spanned) = apply { this.messageSpanned = message }

        fun messageTypeface(typeface: Typeface) = apply { this.messageTypeface = typeface }

        fun messageSizeInPx(size: Float) = apply { this.messageSizeInPx = size }

        fun messageSizeInSp(size: Float) = apply { this.messageSizeInSp = size }

        fun messageColor(@ColorInt color: Int) = apply { this.messageColor = color }

        fun messageColorRes(@ColorRes colorId: Int) = apply {
            this.messageColor = ContextCompat.getColor(activity, colorId)
        }

        fun messageAppearance(@StyleRes appearance: Int) = apply {
            this.messageAppearance = appearance
        }

        fun primaryActionText(text: String) = apply {
            require(progressPosition != ProgressPosition.RIGHT
            ) { "Cannot show action button if right progress is set" }
            this.primaryActionText = text
        }

        fun primaryActionText(@StringRes actionTextId: Int) = apply {
            primaryActionText(activity.getString(actionTextId))
        }

        fun primaryActionText(actionText: Spanned) = apply { this.primaryActionTextSpanned = actionText }

        fun primaryActionTextTypeface(typeface: Typeface) = apply { this.primaryActionTextTypeface = typeface }

        fun primaryActionTextSizeInPx(size: Float) = apply { this.primaryActionTextSizeInPx = size }

        fun primaryActionTextSizeInSp(size: Float) = apply { this.primaryActionTextSizeInSp = size }

        fun primaryActionTextColor(@ColorInt color: Int) = apply { this.primaryActionTextColor = color }

        fun primaryActionTextColorRes(@ColorRes colorId: Int) = apply {
            this.primaryActionTextColor = ContextCompat.getColor(activity, colorId)
        }

        fun primaryActionTextAppearance(@StyleRes appearance: Int) = apply {
            this.primaryActionTextAppearance = appearance
        }

        fun primaryActionTapListener(onActionTapListener: OnActionTapListener) = apply {
            this.onPrimaryActionTapListener = onActionTapListener
        }

        fun positiveActionText(text: String) = apply {
            this.positiveActionText = text
        }

        fun positiveActionText(@StringRes actionTextId: Int) = apply {
            positiveActionText(activity.getString(actionTextId))
        }

        fun positiveActionText(actionText: Spanned) = apply { this.positiveActionTextSpanned = actionText }

        fun positiveActionTextTypeface(typeface: Typeface) = apply { this.positiveActionTextTypeface = typeface }

        fun positiveActionTextSizeInPx(size: Float) = apply { this.positiveActionTextSizeInPx = size }

        fun positiveActionTextSizeInSp(size: Float) = apply { this.positiveActionTextSizeInSp = size }

        fun positiveActionTextColor(@ColorInt color: Int) = apply { this.positiveActionTextColor = color }

        fun positiveActionTextColorRes(@ColorRes colorId: Int) = apply {
            this.positiveActionTextColor = ContextCompat.getColor(activity, colorId)
        }

        fun positiveActionTextAppearance(@StyleRes appearance: Int) = apply {
            this.positiveActionTextAppearance = appearance
        }

        fun positiveActionTapListener(onActionTapListener: OnActionTapListener) = apply {
            this.onPositiveActionTapListener = onActionTapListener
        }

        fun negativeActionText(text: String) = apply {
            this.negativeActionText = text
        }

        fun negativeActionText(@StringRes actionTextId: Int) = apply {
            negativeActionText(activity.getString(actionTextId))
        }

        fun negativeActionText(actionText: Spanned) = apply { this.negativeActionTextSpanned = actionText }

        fun negativeActionTextTypeface(typeface: Typeface) = apply { this.negativeActionTextTypeface = typeface }

        fun negativeActionTextSizeInPx(size: Float) = apply { this.negativeActionTextSizeInPx = size }

        fun negativeActionTextSizeInSp(size: Float) = apply { this.negativeActionTextSizeInSp = size }

        fun negativeActionTextColor(@ColorInt color: Int) = apply { this.negativeActionTextColor = color }

        fun negativeActionTextColorRes(@ColorRes colorId: Int) = apply {
            this.negativeActionTextColor = ContextCompat.getColor(activity, colorId)
        }

        fun negativeActionTextAppearance(@StyleRes appearance: Int) = apply {
            this.negativeActionTextAppearance = appearance
        }

        fun negativeActionTapListener(onActionTapListener: OnActionTapListener) = apply {
            this.onNegativeActionTapListener = onActionTapListener
        }

        @JvmOverloads
        fun showIcon(scale: Float = DEFAULT_ICON_SCALE, scaleType: ScaleType = CENTER_CROP) = apply {
            require(progressPosition != ProgressPosition.LEFT
            ) { "Cannot show icon if left progress is set" }
            require(scale > 0
            ) { "Icon scale cannot be negative or zero" }

            this.showIcon = true
            this.iconScale = scale
            this.iconScaleType = scaleType
        }

        fun icon(icon: Drawable) = apply { this.iconDrawable = icon }

        fun icon(@DrawableRes iconId: Int) = apply {
            this.iconDrawable = ContextCompat.getDrawable(activity, iconId)
        }

        fun icon(bitmap: Bitmap) = apply { this.iconBitmap = bitmap }

        @JvmOverloads
        fun iconColorFilter(@ColorInt color: Int, mode: PorterDuff.Mode? = null) = apply {
            this.iconColorFilter = color
            this.iconColorFilterMode = mode
        }

        @JvmOverloads
        fun iconColorFilterRes(@ColorRes colorId: Int, mode: PorterDuff.Mode? = null) = apply {
            this.iconColorFilter = ContextCompat.getColor(activity, colorId)
            this.iconColorFilterMode = mode
        }

        fun iconAnimation(builder: SnackAnimIconBuilder) = apply { this.iconAnimBuilder = builder }

        fun showProgress(position: ProgressPosition) = apply {
            this.progressPosition = position

            if (progressPosition == ProgressPosition.LEFT && showIcon) {
                throw IllegalArgumentException("Cannot show progress at left if icon is already set")
            }

            if (progressPosition == ProgressPosition.RIGHT && primaryActionText != null) {
                throw IllegalArgumentException("Cannot show progress at right if action button is already set")
            }
        }

        fun progressTint(@ColorInt color: Int) = apply {
            this.progressTint = color
        }

        fun progressTintRes(@ColorRes colorId: Int) = apply {
            this.progressTint = ContextCompat.getColor(activity, colorId)
        }

        fun build(): Snackbar {
            configureAnimation()
            val snackbar = Snackbar(this)
            snackbar.construct()
            return snackbar
        }

        fun show() = build().show()

        private fun configureAnimation() {
            enterAnimBuilder = if (enterAnimBuilder == null) {
                when (gravity) {
                    TOP -> SnackAnim.with(activity).animateBar().enter().fromTop()
                    BOTTOM -> SnackAnim.with(activity).animateBar().enter().fromBottom()
                }
            } else {
                when (gravity) {
                    TOP -> enterAnimBuilder!!.enter().fromTop()
                    BOTTOM -> enterAnimBuilder!!.enter().fromBottom()
                }
            }

            exitAnimBuilder = if (exitAnimBuilder == null) {
                when (gravity) {
                    TOP -> SnackAnim.with(activity).animateBar().exit().fromTop()
                    BOTTOM -> SnackAnim.with(activity).animateBar().exit().fromBottom()
                }
            } else {
                when (gravity) {
                    TOP -> exitAnimBuilder!!.exit().fromTop()
                    BOTTOM -> exitAnimBuilder!!.exit().fromBottom()
                }
            }
        }
    }

    companion object {
        const val DURATION_SHORT = 1000L
        const val DURATION_LONG = 2500L
        const val DURATION_INDEFINITE = -1L
    }

    enum class Gravity { TOP, BOTTOM }

    enum class DismissEvent {
        TIMEOUT,
        MANUAL,
        TAP_OUTSIDE,
        SWIPE
    }

    enum class Vibration { SHOW, DISMISS }

    enum class ProgressPosition { LEFT, RIGHT }

    interface OnActionTapListener {
        fun onActionTapped(bar: Snackbar)
    }

    interface OnBarDismissListener {
        fun onDismissing(bar: Snackbar, isSwiped: Boolean)
        fun onDismissProgress(bar: Snackbar, progress: Float)
        fun onDismissed(bar: Snackbar, event: DismissEvent)
    }

    interface OnTapListener {
        fun onTap(snackbar: Snackbar)
    }

    interface OnBarShowListener {
        fun onShowing(bar: Snackbar)
        fun onShowProgress(bar: Snackbar, progress: Float)
        fun onShown(bar: Snackbar)
    }
}