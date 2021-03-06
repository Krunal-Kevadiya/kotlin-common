package com.kotlinlibrary.utils.ktx.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.view.KeyEvent
import android.view.View
import android.widget.ListAdapter

class AlertDialogBuilder(private val ctx: Context) {
    private var builder: AlertDialog.Builder? = AlertDialog.Builder(ctx)

    /**
     * Returns the [AlertDialog] instance if created.
     * Returns null until the [show] function is called.
     */
    var dialog: AlertDialog? = null
        private set


    fun dismiss() {
        dialog?.dismiss()
    }

    private fun checkBuilder() {
        if (builder == null) {
            throw IllegalStateException("show() was already called for this AlertDialogBuilder")
        }
    }

    /**
     * Create the [AlertDialog] and display it on screen.
     *
     */
    fun show(): AlertDialogBuilder {
        checkBuilder()
        dialog = builder?.create()
        builder = null
        dialog?.show()
        return this
    }

    /**
     * Set the [title] displayed in the dialog.
     */
    fun title(title: CharSequence) {
        checkBuilder()
        builder?.setTitle(title)
    }

    /**
     * Set the title using the given [title] resource id.
     */
    fun title(title: Int) {
        checkBuilder()
        builder?.setTitle(title)
    }

    /**
     * Set the [message] to display.
     */
    fun message(message: CharSequence) {
        checkBuilder()
        builder?.setMessage(message)
    }

    /**
     * Set the message to display using the given [message] resource id.
     */
    fun message(message: Int) {
        checkBuilder()
        builder?.setMessage(message)
    }

    /**
     * Set the resource id of the [Drawable] to be used in the title.
     */
    fun icon(icon: Int) {
        checkBuilder()
        builder?.setIcon(icon)
    }

    /**
     * Set the [icon] Drawable to be used in the title.
     */
    fun icon(icon: Drawable) {
        checkBuilder()
        builder?.setIcon(icon)
    }

    /**
     * Set the title using the custom [view].
     */
    fun customTitle(view: View) {
        checkBuilder()
        builder?.setCustomTitle(view)
    }

    /**
     * Set a custom [view] to be the contents of the Dialog.
     */
    fun customView(view: View) {
        checkBuilder()
        builder?.setView(view)
    }

    /**
     * Set if the dialog is cancellable.
     *
     * @param cancellable if true, the created dialog will be cancellable.
     */
    fun cancellable(cancellable: Boolean = true) {
        checkBuilder()
        builder?.setCancelable(cancellable)
    }

    /**
     * Sets the [callback] that will be called if the dialog is canceled.
     */
    fun onCancel(callback: () -> Unit) {
        checkBuilder()
        builder?.setOnCancelListener { callback() }
    }

    /**
     * Sets the [callback] that will be called if a key is dispatched to the dialog.
     */
    fun onKey(callback: (keyCode: Int, e: KeyEvent) -> Boolean) {
        checkBuilder()
        builder?.setOnKeyListener { _, keyCode, event -> callback(keyCode, event) }
    }

    /**
     * Set a listener to be invoked when the neutral button of the dialog is pressed.
     * 
     * @param neutralText the text resource to display in the neutral button.
     * @param callback the callback that will be called if the neutral button is pressed.
     */
    fun neutralButton(neutralText: Int = android.R.string.ok, callback: DialogInterface.() -> Unit = { dismiss() }) {
        neutralButton(ctx.getString(neutralText), callback)
    }

    /**
     * Set a listener to be invoked when the neutral button of the dialog is pressed.
     *
     * @param neutralText the text to display in the neutral button.
     * @param callback the callback that will be called if the neutral button is pressed.
     */
    fun neutralButton(neutralText: CharSequence, callback: DialogInterface.() -> Unit = { dismiss() }) {
        checkBuilder()
        builder?.setNeutralButton(neutralText) { dialog, _ -> dialog.callback() }
    }

    /**
     * Set a listener to be invoked when the positive button of the dialog is pressed.
     *
     * @param positiveText the text to display in the positive button.
     * @param callback the callback that will be called if the positive button is pressed.
     */
    fun positiveButton(positiveText: Int, callback: DialogInterface.() -> Unit) {
        positiveButton(ctx.getString(positiveText), callback)
    }

    /**
     * Set a listener to be invoked when the positive button of the dialog is pressed.
     *
     * @param callback the callback that will be called if the positive button is pressed.
     */
    fun okButton(callback: DialogInterface.() -> Unit) {
        positiveButton(ctx.getString(android.R.string.ok), callback)
    }

    /**
     * Set a listener to be invoked when the positive button of the dialog is pressed.
     *
     * @param callback the callback that will be called if the positive button is pressed.
     */
    fun yesButton(callback: DialogInterface.() -> Unit) {
        positiveButton(ctx.getString(android.R.string.yes), callback)
    }

    /**
     * Set a listener to be invoked when the positive button of the dialog is pressed.
     *
     * @param positiveText the text to display in the positive button.
     * @param callback the callback that will be called if the positive button is pressed.
     */
    fun positiveButton(positiveText: CharSequence, callback: DialogInterface.() -> Unit) {
        checkBuilder()
        builder?.setPositiveButton(positiveText) { dialog, _ -> dialog.callback() }
    }

    /**
     * Set a listener to be invoked when the negative button of the dialog is pressed.
     *
     * @param negativeText the text to display in the negative button.
     * @param callback the callback that will be called if the negative button is pressed.
     */
    fun negativeButton(negativeText: Int, callback: DialogInterface.() -> Unit = { dismiss() }) {
        negativeButton(ctx.getString(negativeText), callback)
    }

    /**
     * Set a listener to be invoked when the negative button of the dialog is pressed.
     *
     * @param callback the callback that will be called if the negative button is pressed.
     */
    fun cancelButton(callback: DialogInterface.() -> Unit = { dismiss() }) {
        negativeButton(ctx.getString(android.R.string.cancel), callback)
    }

    /**
     * Set a listener to be invoked when the negative button of the dialog is pressed.
     *
     * @param callback the callback that will be called if the negative button is pressed.
     */
    fun noButton(callback: DialogInterface.() -> Unit = { dismiss() }) {
        negativeButton(ctx.getString(android.R.string.no), callback)
    }

    /**
     * Set a listener to be invoked when the negative button of the dialog is pressed.
     *
     * @param negativeText the text to display in the negative button.
     * @param callback the callback that will be called if the negative button is pressed.
     */
    fun negativeButton(negativeText: CharSequence, callback: DialogInterface.() -> Unit = { dismiss() }) {
        checkBuilder()
        builder?.setNegativeButton(negativeText) { dialog, _ -> dialog.callback() }
    }

    fun items(itemsId: Int, callback: (which: Int) -> Unit) {
        items(ctx.resources!!.getTextArray(itemsId), callback)
    }

    fun items(items: List<CharSequence>, callback: (which: Int) -> Unit) {
        items(items.toTypedArray(), callback)
    }

    fun items(items: Array<CharSequence>, callback: (which: Int) -> Unit) {
        checkBuilder()
        builder?.setItems(items) { _, which -> callback(which) }
    }

    fun adapter(adapter: ListAdapter, callback: (which: Int) -> Unit) {
        checkBuilder()
        builder?.setAdapter(adapter) { _, which -> callback(which) }
    }

    fun adapter(cursor: Cursor, labelColumn: String, callback: (which: Int) -> Unit) {
        checkBuilder()
        builder?.setCursor(cursor, { _, which -> callback(which) }, labelColumn)
    }
}
