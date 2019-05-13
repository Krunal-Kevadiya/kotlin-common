package com.kotlinlibrary.utils.ktx

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kotlinlibrary.utils.getActivityFromSource

fun <VDS : ViewDataBinding> Any.setContentBindView(
    @LayoutRes layoutId: Int
): VDS = DataBindingUtil.setContentView(getActivityFromSource(this), layoutId)

fun Any.inflate(
    @LayoutRes layoutId: Int,
    root: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = layoutInflater!!.inflate(layoutId, root, attachToRoot)

fun <VDS : ViewDataBinding> Any.inflateBindView(
    @LayoutRes layoutId: Int,
    root: ViewGroup? = null,
    attachToRoot: Boolean = false
): VDS = DataBindingUtil.inflate(layoutInflater!!, layoutId, root, attachToRoot)

