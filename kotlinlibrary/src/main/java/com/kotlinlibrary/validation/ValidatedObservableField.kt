package com.kotlinlibrary.validation

import androidx.databinding.*
import com.kotlinlibrary.validation.rules.*

class ValidatedObservableField<D> (
    value: String,
    private val onChange: Boolean = false,
    vararg dependencies: Observable
) : CustomObservableField<String, D>(*dependencies) {
    private var isValid = true
    private var successCallback: (() -> Unit)? = null
    private var errorCallback: ((message: D?) -> Unit)? = null
    private val rulesList = mutableListOf<BaseRule<D>>()

    init {
        setValue(value)
    }

    override fun setValue(value: String?) {
        super.setValue(value)
        if (onChange) {
            check()
        }
    }

    override fun setError(error: D?) {
        isValid = false
        super.setError(error)
    }

    fun addRule(rule: BaseRule<D>): ValidatedObservableField<D> {
        rulesList.add(rule)
        return this
    }

    fun addErrorCallback(callback: (message: D?) -> Unit): ValidatedObservableField<D> {
        errorCallback = callback
        return this
    }

    fun addSuccessCallback(callback: () -> Unit): ValidatedObservableField<D> {
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
    fun nonEmpty(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { NonEmptyRule(errorMsg) } ?: NonEmptyRule()
        addRule(rule)
        return this
    }

    fun minLength(length: Int, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { MinLengthRule(length, errorMsg) } ?: MinLengthRule(length)
        addRule(rule)
        return this
    }

    fun maxLength(length: Int, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { MaxLengthRule(length, errorMsg) } ?: MaxLengthRule(length)
        addRule(rule)
        return this
    }

    fun validEmail(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { EmailRule(errorMsg) } ?: EmailRule()
        addRule(rule)
        return this
    }

    fun validNumber(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { ValidNumberRule(errorMsg) } ?: ValidNumberRule()
        addRule(rule)
        return this
    }

    fun greaterThan(number: Number, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { GreaterThanRule(number, errorMsg) } ?: GreaterThanRule(number)
        addRule(rule)
        return this
    }

    fun greaterThanOrEqual(number: Number, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { GreaterThanOrEqualRule(number, errorMsg) }
            ?: GreaterThanOrEqualRule(number)
        addRule(rule)
        return this
    }

    fun lessThan(number: Number, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { LessThanRule(number, errorMsg) } ?: LessThanRule(number)
        addRule(rule)
        return this
    }

    fun lessThanOrEqual(number: Number, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { LessThanOrEqualRule(number, errorMsg) }
            ?: LessThanOrEqualRule(number)
        addRule(rule)
        return this
    }

    fun numberEqualTo(number: Number, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { NumberEqualToRule(number, errorMsg) }
            ?: NumberEqualToRule(number)
        addRule(rule)
        return this
    }

    fun allLowerCase(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { AllLowerCaseRule(errorMsg) } ?: AllLowerCaseRule()
        addRule(rule)
        return this
    }

    fun allUpperCase(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { AllUpperCaseRule(errorMsg) } ?: AllUpperCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneUpperCase(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { AtLeastOneUpperCaseRule(errorMsg) } ?: AtLeastOneUpperCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneLowerCase(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { AtLeastOneLowerCaseRule(errorMsg) } ?: AtLeastOneLowerCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneNumber(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { AtLeastOneNumberCaseRule(errorMsg) }
            ?: AtLeastOneNumberCaseRule()
        addRule(rule)
        return this
    }

    fun noNumbers(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { NoNumbersRule(errorMsg) } ?: NoNumbersRule()
        addRule(rule)
        return this
    }

    fun onlyNumbers(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { OnlyNumbersRule(errorMsg) } ?: OnlyNumbersRule()
        addRule(rule)
        return this
    }

    fun startWithNumber(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { StartsWithNumberRule(errorMsg) } ?: StartsWithNumberRule()
        addRule(rule)
        return this
    }

    fun startWithNonNumber(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { StartsWithNoNumberRule(errorMsg) } ?: StartsWithNoNumberRule()
        addRule(rule)
        return this
    }

    fun noSpecialCharacters(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { NoSpecialCharacterRule(errorMsg) } ?: NoSpecialCharacterRule()
        addRule(rule)
        return this
    }

    fun atleastOneSpecialCharacters(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { AtLeastOneSpecialCharacterRule(errorMsg) }
            ?: AtLeastOneSpecialCharacterRule()
        addRule(rule)
        return this
    }

    fun textEqualTo(target: String, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { TextEqualToRule(target, errorMsg) } ?: TextEqualToRule(target)
        addRule(rule)
        return this
    }

    fun textNotEqualTo(target: String, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { TextNotEqualToRule(target, errorMsg) }
            ?: TextNotEqualToRule(target)
        addRule(rule)
        return this
    }

    fun startsWith(target: String, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { StartsWithRule(target, errorMsg) } ?: StartsWithRule(target)
        addRule(rule)
        return this
    }

    fun endsWith(target: String, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { EndsWithRule(target, errorMsg) } ?: EndsWithRule(target)
        addRule(rule)
        return this
    }

    fun contains(target: String, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { ContainsRule(target, errorMsg) } ?: ContainsRule(target)
        addRule(rule)
        return this
    }

    fun notContains(target: String, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { NotContainsRule(target, errorMsg) } ?: NotContainsRule(target)
        addRule(rule)
        return this
    }

    fun creditCardNumber(
        creditCardErrorMsg: D? = null, minLengthErrorMsg: D? = null,
        maxLengthErrorMsg: D? = null
    ): ValidatedObservableField<D> {
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
        creditCardErrorMsg: D? = null, minLengthErrorMsg: D? = null,
        maxLengthErrorMsg: D? = null
    ): ValidatedObservableField<D> {
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
        creditCardErrorMsg: D? = null, minLengthErrorMsg: D? = null,
        maxLengthErrorMsg: D? = null
    ): ValidatedObservableField<D> {
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

    fun validUrl(errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { ValidUrlRule(errorMsg) } ?: ValidUrlRule()
        addRule(rule)
        return this
    }

    fun regex(pattern: String, errorMsg: D? = null): ValidatedObservableField<D> {
        val rule = errorMsg?.let { RegexRule(pattern, errorMsg) } ?: RegexRule(pattern)
        addRule(rule)
        return this
    }
}
