package com.kotlinlibrary.utils.ktx

import android.content.ClipData
import android.content.Context
import android.net.Uri

fun Context.copyTextToClipboard(value: String) =
    ClipData.newPlainText("text", value).let {
        clipboardManager?.primaryClip = it
    }

fun Context.copyUriToClipboard(uri: Uri) =
    ClipData.newUri(contentResolver, "uri", uri).let {
        clipboardManager?.primaryClip = it
    }

fun String.copyToClipboard(context: Context, label: String) =
    ClipData.newPlainText(label, this).let {
        context.clipboardManager?.primaryClip = it
    }

fun Context.getTextFromClipboard(): CharSequence {
    val clipData = clipboardManager?.primaryClip
    if (clipData != null && clipData.itemCount > 0) {
        return clipData.getItemAt(0).coerceToText(this)
    }

    return ""
}

fun Context.getUriFromClipboard(): Uri? {
    val clipData = clipboardManager?.primaryClip
    if (clipData != null && clipData.itemCount > 0) {
        return clipData.getItemAt(0).uri
    }

    return null
}
