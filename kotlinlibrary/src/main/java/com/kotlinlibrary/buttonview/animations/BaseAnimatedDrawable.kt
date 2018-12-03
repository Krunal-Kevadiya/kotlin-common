package com.kotlinlibrary.buttonview.animations

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable

abstract class BaseAnimatedDrawable : Drawable(), Animatable {

    abstract fun dispose()

    abstract fun setupAnimations()
}
