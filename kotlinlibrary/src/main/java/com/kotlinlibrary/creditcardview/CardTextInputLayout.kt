package com.kotlinlibrary.creditcardview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.kotlinlibrary.utils.ktx.logs
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class CardTextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {
    private var hasValidInput: Boolean = false
    private var collapsingTextHelper: Any? = null
    private var bounds: Rect? = null
    private var recalculateMethod: Method? = null
    private var hasUpdated: Boolean = false

    init {
        init()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        adjustBounds()
        if (!hasUpdated) {
            try {
                editText!!.inputType = InputType.TYPE_CLASS_NUMBER
                updatePasswordToggleView()
                passwordVisibilityToggleRequested()
            } catch (e: NoSuchMethodException) {
                logs(e)
            } catch (e: InvocationTargetException) {
                logs(e)
            } catch (e: IllegalAccessException) {
                logs(e)
            } catch (e: NoSuchFieldException) {
                logs(e)
            }

            hasUpdated = true
        }
    }

    private fun init() {
        try {
            val cthField = TextInputLayout::class.java.getDeclaredField("mCollapsingTextHelper")
            cthField.isAccessible = true
            collapsingTextHelper = cthField.get(this)
            val boundsField = collapsingTextHelper!!.javaClass.getDeclaredField("mCollapsedBounds")
            boundsField.isAccessible = true
            bounds = boundsField.get(collapsingTextHelper) as Rect

            recalculateMethod = collapsingTextHelper!!.javaClass.getDeclaredMethod("recalculate")
        } catch (e: NoSuchFieldException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            logs(e)
        } catch (e: IllegalAccessException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            logs(e)
        } catch (e: NoSuchMethodException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            logs(e)
        }
    }

    private fun adjustBounds() {
        if (collapsingTextHelper == null) {
            return
        }

        try {
            bounds!!.left = editText!!.left + editText!!.paddingLeft
            recalculateMethod!!.invoke(collapsingTextHelper)
        } catch (e: InvocationTargetException) {
            logs(e)
        } catch (e: IllegalAccessException) {
            logs(e)
        } catch (e: IllegalArgumentException) {
            logs(e)
        }
    }

    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    private fun toggleEnabled(fieldName: String, value: Boolean) {
        val cthField: Field? = TextInputLayout::class.java.getDeclaredField(fieldName)
        cthField!!.isAccessible = true
        cthField.setBoolean(this, value)
    }

    @Throws(NoSuchMethodException::class, InvocationTargetException::class, IllegalAccessException::class)
    private fun updatePasswordToggleView() {
        val updatePasswordToggleView = TextInputLayout::class.java.getDeclaredMethod("updatePasswordToggleView")
        updatePasswordToggleView.isAccessible = true
        updatePasswordToggleView.invoke(this)
    }

    @SuppressLint("RestrictedApi")
    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    fun passwordVisibilityToggleRequested() {
        // Store the current cursor position
        val selection = editText!!.selectionEnd

        if (editText!!.text.toString().isNotEmpty()) {
            editText!!.transformationMethod = CreditCardTransformation.instance
            toggleEnabled("mPasswordToggledVisible", false)
        } else {
            editText!!.transformationMethod = null
            toggleEnabled("mPasswordToggledVisible", true)
        }
        // And restore the cursor position
        editText!!.setSelection(selection)
    }

    fun hasValidInput(): Boolean {
        return this.hasValidInput
    }

    fun setHasValidInput(hasValidInput: Boolean): CardTextInputLayout {
        this.hasValidInput = hasValidInput
        return this
    }
}
