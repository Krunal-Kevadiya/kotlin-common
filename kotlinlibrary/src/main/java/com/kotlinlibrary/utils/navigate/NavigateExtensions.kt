package com.kotlinlibrary.utils.navigate

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kotlinlibrary.R
import com.kotlinlibrary.utils.getActivityFromSource
import com.kotlinlibrary.utils.getAppCompatActivityFromSource

//For Activity
fun Any.launchActivity(
    intent: Intent,
    finishCurrent: Boolean = false,
    applyAnimation: Boolean = true,
    flags: Int? = null,
    resultCode: Int? = null,
    vararg params: Pair<String, Any?>
) {
    val activity = getActivityFromSource(this)
    if (finishCurrent)
        finishCurrentActivity()
    if (params.isNotEmpty())
        fillIntentArguments(intent, params)
    if(flags != null)
        intent.addFlags(flags)
    if (resultCode != null)
        activity.startActivityForResult(intent, resultCode)
    else
        activity.startActivity(intent)
    if (applyAnimation)
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
}

inline fun <reified T : Activity> Any.launchActivity(
    finishCurrent: Boolean = false,
    applyAnimation: Boolean = true,
    flags: Int? = null,
    resultCode: Int? = null,
    vararg params: Pair<String, Any?>
) {
    internalStartActivity(finishCurrent, applyAnimation, flags, T::class.java, resultCode, params)
}

fun Any.internalStartActivity(
    finishCurrent: Boolean = false,
    applyAnimation: Boolean = true,
    flags: Int? = null,
    activityClass: Class<out Activity>,
    resultCode: Int? = null,
    params: Array<out Pair<String, Any?>>
) {
    val activity = getActivityFromSource(this)
    if (finishCurrent)
        finishCurrentActivity()
    if (resultCode != null)
        activity.startActivityForResult(createIntent(activity, activityClass, flags, params), resultCode)
    else
        activity.startActivity(createIntent(activity, activityClass, flags, params))
    if (applyAnimation)
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
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

fun Any.finishCurrentActivity(applyAnimation: Boolean = true) {
    val activity = getActivityFromSource(this)
    activity.finish()
    if (applyAnimation)
        activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
}

fun Any.finishAllCurrentActivity(applyAnimation: Boolean = true) {
    val activity = getActivityFromSource(this)
    activity.finishAffinity()
    if (applyAnimation)
        activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
}

//Fragment
fun Any.showFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    func: NavigationOptions.() -> Unit = {}
) {
    val activity = getAppCompatActivityFromSource(this)
    val options = NavigationOptions()
    func.invoke(options)
    val fragmentManager = activity.supportFragmentManager
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

fun Any.showChildFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    func: NavigationOptions.() -> Unit = {}
) {
    //val activity = getAppCompatActivityFrom(this)
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

fun Any.showDialogFragment(
    dialogFragment: DialogFragment,
    func: NavigationOptions.() -> Unit = {}
) {
    val activity = getAppCompatActivityFromSource(this)
    val options = NavigationOptions()
    func.invoke(options)
    val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
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

fun Any.getCurrentFragmentManager(): FragmentManager? {
    val activity = getAppCompatActivityFromSource(this)
    return activity.supportFragmentManager
}

fun Any.getCurrentActiveFragment(@IdRes frameId: Int): Fragment? {
    val activity = getAppCompatActivityFromSource(this)
    return activity.supportFragmentManager.findFragmentById(frameId)
}

fun Any.clearAllFragment() {
    val activity = getAppCompatActivityFromSource(this)
    activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun Any.onBackTrackFragment(): Boolean {
    val activity = getAppCompatActivityFromSource(this)
    val fragmentManager = activity.supportFragmentManager
    return if (fragmentManager.backStackEntryCount != 0) {
        fragmentManager.popBackStack()
        true
    } else
        false
}