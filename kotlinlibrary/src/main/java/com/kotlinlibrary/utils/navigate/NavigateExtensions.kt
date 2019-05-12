package com.kotlinlibrary.utils.navigate

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kotlinlibrary.R

//For Activity
inline fun <reified T : Activity> Activity.launchActivity(
    finishCurrent: Boolean = false,
    applyAnimation: Boolean = true,
    flags: Int? = null,
    resultCode: Int? = null,
    vararg params: Pair<String, Any>
) {
    internalStartActivity(finishCurrent, applyAnimation, flags, T::class.java, resultCode, params)
}

fun Activity.internalStartActivity(
    finishCurrent: Boolean = false,
    applyAnimation: Boolean = true,
    flags: Int? = null,
    activity: Class<out Activity>,
    resultCode: Int? = null,
    params: Array<out Pair<String, Any>>
) {
    if (finishCurrent)
        finishCurrentActivity()
    if (resultCode != null)
        startActivityForResult(createIntent(this, activity, flags, params), resultCode)
    else
        startActivity(createIntent(this, activity, flags, params))
    if (applyAnimation)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
}

fun <T> createIntent(
    ctx: Context,
    clazz: Class<out T>,
    flags: Int? = null,
    params: Array<out Pair<String, Any?>>
): Intent {
    val intent = Intent(ctx, clazz)
    if (params.isNotEmpty())
        fillIntentArguments(intent, params)
    if(flags != null)
        intent.addFlags(flags)
    return intent
}

private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        when (val value = it.second) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}

fun Activity.finishCurrentActivity(applyAnimation: Boolean = true) {
    finish()
    if (applyAnimation)
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
}

fun Activity.finishAllCurrentActivity(applyAnimation: Boolean = true) {
    finishAffinity()
    if (applyAnimation)
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
}

//Fragment
fun AppCompatActivity.showFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    func: NavigationOptions.() -> Unit = {}
) {
    val options = NavigationOptions()
    func.invoke(options)
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    if (options.addToBackStack)
        fragmentTransaction.addToBackStack(null)

    if (options.targetOption.fragmentToTarget != null)
        fragment.setTargetFragment(options.targetOption.fragmentToTarget, options.targetOption.requestCode)

    if (options.animOption.animEnter != 0 || options.animOption.animExit != 0)
        fragmentTransaction.setCustomAnimations(options.animOption.animEnter, options.animOption.animExit)

    options.animOption.views?.forEach { view ->
        fragmentTransaction.addSharedElement(view.first, view.second)
    }

    if (options.transactionType == TransactionType.FragmentType.Add)
        fragmentTransaction.add(frameId, fragment, options.tag)
    else
        fragmentTransaction.replace(frameId, fragment, options.tag)
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.showChildFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    func: NavigationOptions.() -> Unit = {}
) {
    val options = NavigationOptions()
    func.invoke(options)
    val fragmentManager = options.childFragmentOption.parentFragment!!.childFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    if (options.addToBackStack)
        fragmentTransaction.addToBackStack(null)

    if (options.targetOption.fragmentToTarget != null)
        fragment.setTargetFragment(options.targetOption.fragmentToTarget, options.targetOption.requestCode)

    if (options.animOption.animEnter != 0 || options.animOption.animExit != 0)
        fragmentTransaction.setCustomAnimations(options.animOption.animEnter, options.animOption.animExit)

    options.animOption.views?.forEach { view ->
        fragmentTransaction.addSharedElement(view.first, view.second)
    }

    if (options.transactionType == TransactionType.FragmentType.Add)
        fragmentTransaction.add(frameId, fragment, options.tag)
    else
        fragmentTransaction.replace(frameId, fragment, options.tag)
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.showDialogFragment(
    dialogFragment: DialogFragment,
    func: NavigationOptions.() -> Unit = {}
) {
    val options = NavigationOptions()
    func.invoke(options)
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    if (options.addToBackStack)
        fragmentTransaction.addToBackStack(null)

    if (options.targetOption.fragmentToTarget != null)
        dialogFragment.setTargetFragment(options.targetOption.fragmentToTarget, options.targetOption.requestCode)

    if (options.targetOption.targetDialogFragment != null)
        dialogFragment.setTargetFragment(options.targetOption.targetDialogFragment, options.targetOption.requestCode)

    if (options.animOption.animEnter != 0 || options.animOption.animExit != 0)
        fragmentTransaction.setCustomAnimations(options.animOption.animEnter, options.animOption.animExit)

    options.animOption.views?.forEach { view ->
        fragmentTransaction.addSharedElement(view.first, view.second)
    }

    if (options.transactionType == TransactionType.DialogFragmentType.Show)
        fragmentTransaction.show(dialogFragment)
    else
        fragmentTransaction.hide(dialogFragment)
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.getCurrentFragmentManager(): FragmentManager? {
    return supportFragmentManager
}

fun AppCompatActivity.getCurrentActiveFragment(@IdRes frameId: Int): Fragment? {
    return supportFragmentManager.findFragmentById(frameId)
}

fun AppCompatActivity.clearAllFragment() {
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun AppCompatActivity.onBackTrackFragment(): Boolean {
    val fragmentManager = supportFragmentManager
    return if (fragmentManager.backStackEntryCount != 0) {
        fragmentManager.popBackStack()
        true
    } else
        false
}

/**
 * Fragment
 */
fun Fragment.showFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    func: NavigationOptions.() -> Unit = {}
) {
    (context as AppCompatActivity).showFragment(frameId, fragment, func)
}

fun Fragment.showChildFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    func: NavigationOptions.() -> Unit = {}
) {
    (context as AppCompatActivity).showChildFragment(frameId, fragment, func)
}

fun Fragment.showDialogFragment(
    dialogFragment: DialogFragment,
    func: NavigationOptions.() -> Unit = {}
) {
    (context as AppCompatActivity).showDialogFragment(dialogFragment, func)
}

fun Fragment.getCurrentFragmentManager(): FragmentManager? {
    return (context as AppCompatActivity).getCurrentFragmentManager()
}

fun Fragment.getCurrentActiveFragment(@IdRes frameId: Int): Fragment? {
    return (context as AppCompatActivity).getCurrentActiveFragment(frameId)
}

fun Fragment.clearAllFragment() {
    (context as AppCompatActivity).clearAllFragment()
}
