package com.kotlinlibrary.utils

import android.app.Activity
import android.app.Application
import android.app.DialogFragment
import android.app.Fragment
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

@Suppress("DEPRECATION")
fun getContextFromSource(source: Any): Context {
    return when (source) {
        is Context -> source
        is Activity -> source
        is Fragment -> source.activity
        is androidx.fragment.app.Fragment -> source.context ?: throwContextException(source)
        is DialogFragment -> source.activity
        is androidx.fragment.app.DialogFragment -> source.context ?: throwContextException(source)
        is Application -> source.applicationContext
        is View -> source.context
        is ContextWrapper -> source.baseContext
        else -> throwContextException(source)
    }
}
private fun throwContextException(source: Any): Nothing = throw IllegalArgumentException("Unable to get Context on type ${source.javaClass.simpleName}")

@Suppress("DEPRECATION")
fun getActivityFromSource(source: Any): Activity {
    return when (source) {
        is Context -> source as? Activity ?: throwActivityException(source)
        is Activity -> source
        is Fragment -> source.activity
        is androidx.fragment.app.Fragment -> source.context as? Activity ?: throwActivityException(source)
        is DialogFragment -> source.activity
        is androidx.fragment.app.DialogFragment -> source.context as? Activity ?: throwActivityException(source)
        is Application -> source.applicationContext as? Activity ?: throwActivityException(source)
        is View -> source.context as? Activity ?: throwActivityException(source)
        is ContextWrapper -> source.baseContext as? Activity ?: throwActivityException(source)
        else -> throwActivityException(source)
    }
}
private fun throwActivityException(source: Any): Nothing = throw IllegalArgumentException("Unable to get Activity on type ${source.javaClass.simpleName}")

@Suppress("DEPRECATION")
fun getFragmentActivityFromSource(source: Any): FragmentActivity {
    return when (source) {
        is Context -> source as? FragmentActivity ?: throwFragmentActivityException(source)
        is Activity -> source as? FragmentActivity ?: throwFragmentActivityException(source)
        is Fragment -> source.activity as? FragmentActivity ?: throwFragmentActivityException(source)
        is androidx.fragment.app.Fragment -> source.context as? FragmentActivity ?: throwFragmentActivityException(source)
        is DialogFragment -> source.activity as? FragmentActivity ?: throwFragmentActivityException(source)
        is androidx.fragment.app.DialogFragment -> source.context as? FragmentActivity ?: throwFragmentActivityException(source)
        is Application -> source.applicationContext as? FragmentActivity ?: throwFragmentActivityException(source)
        is View -> source.context as? FragmentActivity ?: throwFragmentActivityException(source)
        is ContextWrapper -> source.baseContext as? FragmentActivity ?: throwFragmentActivityException(source)
        else -> throwFragmentActivityException(source)
    }
}
private fun throwFragmentActivityException(source: Any): Nothing = throw IllegalArgumentException("Unable to get FragmentActivity on type ${source.javaClass.simpleName}")

@Suppress("DEPRECATION")
fun getAppCompatActivityFromSource(source: Any): AppCompatActivity {
    return when (source) {
        is Context -> source as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is Activity -> source as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is Fragment -> source.activity as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is androidx.fragment.app.Fragment -> source.context as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is DialogFragment -> source.activity as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is androidx.fragment.app.DialogFragment -> source.context as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is Application -> source.applicationContext as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is View -> source.context as? AppCompatActivity ?: throwAppCompatActivityException(source)
        is ContextWrapper -> source.baseContext as? AppCompatActivity ?: throwAppCompatActivityException(source)
        else -> throwAppCompatActivityException(source)
    }
}
private fun throwAppCompatActivityException(source: Any): Nothing = throw IllegalArgumentException("Unable to get AppCompatActivity on type ${source.javaClass.simpleName}")
