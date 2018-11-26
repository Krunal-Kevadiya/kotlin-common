package com.kotlinlibrary.recycleradapter.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun ViewGroup.inflate(@LayoutRes resource: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(context).inflate(resource, this, attachToRoot)

fun ViewGroup.inflateDatabinding(@LayoutRes resource: Int, attachToRoot: Boolean = false): ViewDataBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), resource, this, attachToRoot)