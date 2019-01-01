package com.kotlinlibrary.validation

import androidx.databinding.*
import com.kotlinlibrary.validation.rules.*

class ValidatedObservableField<T : Any>(
    value: T,
    private val onChange: Boolean = false,
    vararg dependencies: Observable
) : CustomObservableField<T, String>(*dependencies) {
    private var isValid = true
    private var successCallback: (() -> Unit)? = null
    private var errorCallback: ((message: String?) -> Unit)? = null
    private val rulesList = mutableListOf<BaseRule>()

    init {
        setValue(value)
    }

    override fun setValue(value: T?) {
        super.setValue(value)
        if (onChange) {
            check()
        }
    }

    override fun setError(error: String?) {
        isValid = false
        super.setError(error)
    }

    fun addRule(rule: BaseRule): ValidatedObservableField<T> {
        rulesList.add(rule)
        return this
    }

    fun addErrorCallback(callback: (message: String?) -> Unit): ValidatedObservableField<T> {
        errorCallback = callback
        return this
    }

    fun addSuccessCallback(callback: () -> Unit): ValidatedObservableField<T> {
        successCallback = callback
        return this
    }

    fun check(): Boolean {
        reset()

        for (rule in rulesList) {
            if (!rule.validate(getValue().toString())) {
                setError(rule.getErrorMessage())
                break
            }
        }
        // Invoking callbacks
        if (isValid)
            successCallback?.invoke()
        else
            errorCallback?.invoke(getError())

        notifyChange()
        return isValid
    }

    private fun reset() {
        this.setError(null)
        isValid = true
    }

    // Rules
    fun nonEmpty(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { NonEmptyRule(errorMsg) } ?: NonEmptyRule()
        addRule(rule)
        return this
    }

    fun minLength(length: Int, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { MinLengthRule(length, errorMsg) } ?: MinLengthRule(length)
        addRule(rule)
        return this
    }

    fun maxLength(length: Int, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { MaxLengthRule(length, errorMsg) } ?: MaxLengthRule(length)
        addRule(rule)
        return this
    }

    fun validEmail(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { EmailRule(errorMsg) } ?: EmailRule()
        addRule(rule)
        return this
    }

    fun validNumber(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { ValidNumberRule(errorMsg) } ?: ValidNumberRule()
        addRule(rule)
        return this
    }

    fun greaterThan(number: Number, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { GreaterThanRule(number, errorMsg) } ?: GreaterThanRule(number)
        addRule(rule)
        return this
    }

    fun greaterThanOrEqual(number: Number, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { GreaterThanOrEqualRule(number, errorMsg) }
            ?: GreaterThanOrEqualRule(number)
        addRule(rule)
        return this
    }

    fun lessThan(number: Number, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { LessThanRule(number, errorMsg) } ?: LessThanRule(number)
        addRule(rule)
        return this
    }

    fun lessThanOrEqual(number: Number, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { LessThanOrEqualRule(number, errorMsg) }
            ?: LessThanOrEqualRule(number)
        addRule(rule)
        return this
    }

    fun numberEqualTo(number: Number, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { NumberEqualToRule(number, errorMsg) }
            ?: NumberEqualToRule(number)
        addRule(rule)
        return this
    }

    fun allLowerCase(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { AllLowerCaseRule(errorMsg) } ?: AllLowerCaseRule()
        addRule(rule)
        return this
    }

    fun allUpperCase(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { AllUpercCaseRule(errorMsg) } ?: AllUpercCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneUpperCase(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { AtLeastOneUpercCaseRule(errorMsg) } ?: AtLeastOneUpercCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneLowerCase(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { AtLeastOneLowerCaseRule(errorMsg) } ?: AtLeastOneLowerCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneNumber(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { AtLeastOneNumberCaseRule(errorMsg) }
            ?: AtLeastOneNumberCaseRule()
        addRule(rule)
        return this
    }

    fun noNumbers(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { NoNumbersRule(errorMsg) } ?: NoNumbersRule()
        addRule(rule)
        return this
    }

    fun onlyNumbers(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { OnlyNumbersRule(errorMsg) } ?: OnlyNumbersRule()
        addRule(rule)
        return this
    }

    fun startWithNumber(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { StartsWithNumberRule(errorMsg) } ?: StartsWithNumberRule()
        addRule(rule)
        return this
    }

    fun startWithNonNumber(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { StartsWithNoNumberRule(errorMsg) } ?: StartsWithNoNumberRule()
        addRule(rule)
        return this
    }

    fun noSpecialCharacters(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { NoSpecialCharacterRule(errorMsg) } ?: NoSpecialCharacterRule()
        addRule(rule)
        return this
    }

    fun atleastOneSpecialCharacters(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { AtleastOneSpecialCharacterRule(errorMsg) }
            ?: AtleastOneSpecialCharacterRule()
        addRule(rule)
        return this
    }

    fun textEqualTo(target: String, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { TextEqualToRule(target, errorMsg) } ?: TextEqualToRule(target)
        addRule(rule)
        return this
    }

    fun textNotEqualTo(target: String, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { TextNotEqualToRule(target, errorMsg) }
            ?: TextNotEqualToRule(target)
        addRule(rule)
        return this
    }

    fun startsWith(target: String, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { StartsWithRule(target, errorMsg) } ?: StartsWithRule(target)
        addRule(rule)
        return this
    }

    fun endsWith(target: String, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { EndsWithRule(target, errorMsg) } ?: EndsWithRule(target)
        addRule(rule)
        return this
    }

    fun contains(target: String, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { ContainsRule(target, errorMsg) } ?: ContainsRule(target)
        addRule(rule)
        return this
    }

    fun notContains(target: String, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { NotContainsRule(target, errorMsg) } ?: NotContainsRule(target)
        addRule(rule)
        return this
    }

    fun creditCardNumber(
        creditCardErrorMsg: String? = null, minLengthErrorMsg: String? = null,
        maxLengthErrorMsg: String? = null
    ): ValidatedObservableField<T> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg) }
            ?: MinLengthRule(16)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg) }
            ?: MaxLengthRule(16)
        val creditCardRule = creditCardErrorMsg?.let { CreditCardRule(creditCardErrorMsg) }
            ?: CreditCardRule()

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun creditCardNumberWithSpaces(
        creditCardErrorMsg: String? = null, minLengthErrorMsg: String? = null,
        maxLengthErrorMsg: String? = null
    ): ValidatedObservableField<T> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg) }
            ?: MinLengthRule(19)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg) }
            ?: MaxLengthRule(19)
        val creditCardRule = creditCardErrorMsg?.let { CreditCardWithSpacesRule(creditCardErrorMsg) }
            ?: CreditCardWithSpacesRule()

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun creditCardNumberWithDashes(
        creditCardErrorMsg: String? = null, minLengthErrorMsg: String? = null,
        maxLengthErrorMsg: String? = null
    ): ValidatedObservableField<T> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg) }
            ?: MinLengthRule(19)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg) }
            ?: MaxLengthRule(19)
        val creditCardRule = creditCardErrorMsg?.let { CreditCardWithDashesRule(creditCardErrorMsg) }
            ?: CreditCardWithDashesRule()

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun validUrl(errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { ValidUrlRule(errorMsg) } ?: ValidUrlRule()
        addRule(rule)
        return this
    }

    fun regex(pattern: String, errorMsg: String? = null): ValidatedObservableField<T> {
        val rule = errorMsg?.let { RegexRule(pattern, errorMsg) } ?: RegexRule(pattern)
        addRule(rule)
        return this
    }
}
