package com.kotlinlibrary.utils.navigate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kotlinlibrary.R

val addToBackStrack = true
val skipToBackStrack = false
fun Activity.launchActivity(
    intent: Intent,
    finishCurrent: Boolean = false,
    resultCode: Int? = null
) {
    if (finishCurrent)
        finishCurrentActivity()
    if (resultCode != null)
        startActivityForResult(intent, resultCode)
    else
        startActivity(intent)
    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
}

inline fun <reified T : Activity> Activity.launchActivity(
    finishCurrent: Boolean,
    bundle: Bundle? = null,
    resultCode: Int? = null
) {
    if (finishCurrent)
        finishCurrentActivity()
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    if (resultCode != null)
        startActivityForResult(intent, resultCode)
    else
        startActivity(intent)
    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
}

fun Activity.finishCurrentActivity() {
    finish()
    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
}

fun Activity.finishAllCurrentActivity() {
    finishAffinity()
    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
}

/**
 * Fragment
 */
fun Fragment.addFragment(@IdRes frameId: Int, fragment: Fragment) {
    addFragment(frameId, fragment, null, 0)
}

fun Fragment.addFragment(@IdRes frameId: Int, fragment: Fragment, addToBackStack: Boolean) {
    addFragment(frameId, fragment, null, addToBackStack, 0)
}

fun Fragment.addFragment(@IdRes frameId: Int, fragment: Fragment, fragmentToTarget: Fragment?, requestcode: Int) {
    addFragment(frameId, fragment, fragmentToTarget, addToBackStrack, requestcode)
}

fun Fragment.addFragment(@IdRes frameId: Int, fragment: Fragment, fragmentToTarget: Fragment?, addToBackStack: Boolean, requestcode: Int) {
    loadFragment(frameId, fragment, fragmentToTarget, TransactionType.ADD, addToBackStack, requestcode)
}

fun Fragment.replaceFragment(@IdRes frameId: Int, fragment: Fragment) {
    replaceFragment(frameId, fragment, null, 0)
}

fun Fragment.replaceFragment(@IdRes frameId: Int, fragment: Fragment, addToBackStack: Boolean) {
    replaceFragment(frameId, fragment, null, addToBackStack, 0)
}

fun Fragment.replaceFragment(@IdRes frameId: Int, fragment: Fragment, fragmentToTarget: Fragment?, requestcode: Int) {
    replaceFragment(frameId, fragment, fragmentToTarget, addToBackStrack, requestcode)
}

fun Fragment.replaceFragment(@IdRes frameId: Int, fragment: Fragment, fragmentToTarget: Fragment?, addToBackStack: Boolean, requestcode: Int) {
    loadFragment(frameId, fragment, fragmentToTarget, TransactionType.REPLACE, addToBackStack, requestcode)
}

fun Fragment.replaceChildFragment(@IdRes frameId: Int, currentFragment: Fragment, fragment: Fragment, isAddToBackStack: Boolean) {
    (context as AppCompatActivity).replaceChildFragment(frameId, currentFragment, fragment, isAddToBackStack)
}

fun Fragment.loadFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    fragmentToTarget: Fragment?,
    transactionType: TransactionType,
    addToBackStack: Boolean,
    requestcode: Int
) {
    (context as AppCompatActivity).loadFragment(frameId, fragment, fragmentToTarget, transactionType, addToBackStack, requestcode)
}

fun Fragment.showDialogFragment(dialogFragment: DialogFragment) {
    (context as AppCompatActivity).showDialogFragment(dialogFragment)
}

fun Fragment.showDialogFragment(dialogFragment: DialogFragment, targetFragment: Fragment, requestCode: Int) {
    (context as AppCompatActivity).showDialogFragment(dialogFragment, targetFragment, requestCode)
}

fun Fragment.showDialogFragment(dialogFragment: DialogFragment, targetFragment: DialogFragment, requestCode: Int) {
    (context as AppCompatActivity).showDialogFragment(dialogFragment, targetFragment, requestCode)
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

fun AppCompatActivity.addFragment(@IdRes frameId: Int, fragment: Fragment) {
    addFragment(frameId, fragment, null, 0)
}

fun AppCompatActivity.addFragment(@IdRes frameId: Int, fragment: Fragment, addToBackStack: Boolean) {
    addFragment(frameId, fragment, null, addToBackStack, 0)
}

fun AppCompatActivity.addFragment(@IdRes frameId: Int, fragment: Fragment, fragmentToTarget: Fragment?, requestcode: Int) {
    addFragment(frameId, fragment, fragmentToTarget, addToBackStrack, requestcode)
}

fun AppCompatActivity.addFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    fragmentToTarget: Fragment?,
    addToBackStack: Boolean,
    requestcode: Int
) {
    loadFragment(frameId, fragment, fragmentToTarget, TransactionType.ADD, addToBackStack, requestcode)
}

fun AppCompatActivity.replaceFragment(@IdRes frameId: Int, fragment: Fragment) {
    replaceFragment(frameId, fragment, null, 0)
}

fun AppCompatActivity.replaceFragment(@IdRes frameId: Int, fragment: Fragment, addToBackStack: Boolean) {
    replaceFragment(frameId, fragment, null, addToBackStack, 0)
}

fun AppCompatActivity.replaceFragment(@IdRes frameId: Int, fragment: Fragment, fragmentToTarget: Fragment?, requestcode: Int) {
    replaceFragment(frameId, fragment, fragmentToTarget, addToBackStrack, requestcode)
}

fun AppCompatActivity.replaceFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    fragmentToTarget: Fragment?,
    addToBackStack: Boolean,
    requestcode: Int
) {
    loadFragment(frameId, fragment, fragmentToTarget, TransactionType.REPLACE, addToBackStack, requestcode)
}

fun AppCompatActivity.replaceChildFragment(@IdRes frameId: Int, currentFragment: Fragment, fragment: Fragment, isAddToBackStack: Boolean) {
    val fragmentTransaction = currentFragment.childFragmentManager.beginTransaction()
    fragmentTransaction.replace(frameId, fragment)

    if (isAddToBackStack)
        fragmentTransaction.addToBackStack(null)
    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.loadFragment(
    @IdRes frameId: Int,
    fragment: Fragment,
    fragmentToTarget: Fragment?,
    transactionType: TransactionType,
    addToBackStack: Boolean,
    requestcode: Int
) {
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    if (addToBackStack)
        fragmentTransaction.addToBackStack(null)
    if (fragmentToTarget != null)
        fragment.setTargetFragment(fragmentToTarget, requestcode)
    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    if (transactionType == TransactionType.ADD)
        fragmentTransaction.add(frameId, fragment)
    else
        fragmentTransaction.replace(frameId, fragment)
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.showDialogFragment(dialogFragment: DialogFragment) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.add(dialogFragment, null)
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.showDialogFragment(dialogFragment: DialogFragment, targetFragment: Fragment, requestCode: Int) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    dialogFragment.setTargetFragment(targetFragment, requestCode)
    fragmentTransaction.add(dialogFragment, null)
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.showDialogFragment(dialogFragment: DialogFragment, targetFragment: DialogFragment, requestCode: Int) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    dialogFragment.setTargetFragment(targetFragment, requestCode)
    fragmentTransaction.add(dialogFragment, null)
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
