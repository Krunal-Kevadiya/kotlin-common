package com.kotlinlibrary.snackbar.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.JELLY_BEAN
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.kotlinlibrary.R
import com.kotlinlibrary.snackbar.view.ShadowView.ShadowType.BOTTOM
import com.kotlinlibrary.snackbar.view.ShadowView.ShadowType.TOP

internal class ShadowView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    internal fun applyShadow(type: ShadowType) {
        when (type) {
            TOP -> setShadow(R.drawable.sb_shadow_top)
            BOTTOM -> setShadow(R.drawable.sb_shadow_bottom)
        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("ObsoleteSdkInt")
    private fun setShadow(@DrawableRes id: Int) {
        val shadow = ContextCompat.getDrawable(context, id)
        if (SDK_INT >= JELLY_BEAN) {
            this.background = shadow
        } else {
            this.setBackgroundDrawable(shadow)
        }
    }

    enum class ShadowType {
        TOP, BOTTOM
    }
}