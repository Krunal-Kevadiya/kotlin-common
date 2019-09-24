package com.kotlinlibrary.utils.ktx

import android.content.ClipData
import android.content.Context
import android.net.Uri
import com.kotlinlibrary.utils.getContextFromSource

fun Any.copyTextToClipboard(value: String) =
    ClipData.newPlainText("text", value).let {
        this@copyTextToClipboard.clipboardManager?.setPrimaryClip(it)
    }

fun Any.copyUriToClipboard(uri: Uri) =
    ClipData.newUri(getContextFromSource(this).contentResolver, "uri", uri).let {
        this@copyUriToClipboard.clipboardManager?.setPrimaryClip(it)
    }

fun String.copyToClipboard(context: Context, label: String) =
    ClipData.newPlainText(label, this).let {
        context.clipboardManager?.setPrimaryClip(it)
    }

fun Any.getTextFromClipboard(): CharSequence {
    val clipData = clipboardManager?.primaryClip
    if (clipData != null && clipData.itemCount > 0) {
        return clipData.getItemAt(0).coerceToText(getContextFromSource(this))
    }
    return ""
}

fun Any.getUriFromClipboard(): Uri? {
    val clipData = clipboardManager?.primaryClip
    if (clipData != null && clipData.itemCount > 0) {
        return clipData.getItemAt(0).uri
    }
    return null
}
