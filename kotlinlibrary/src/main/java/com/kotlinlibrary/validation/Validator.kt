package com.kotlinlibrary.validation

import com.kotlinlibrary.validation.rules.BaseRule
import com.kotlinlibrary.validation.rules.*

/**
 * The core Validator builder class for validation operations and checks!
 * This class allows developers to process single or multiple validation checks on input views.
 */
class Validator<D>(val text: String) {
    /*
     * Boolean to determine whether all the validations have passed successfully!
     * If any validation check is failed, then the value to
     * false and result is returned to developer
     */
    private var isValid = true

    /*
     The error message to be sent in the error callback
     */
    private var errorMessage: D? = null

    /*
     * In case of validation error or failure, this callback is invoked
     */
    private var errorCallback: ((message: D?) -> Unit)? = null

    /*
     * In case of validation success, this callback is invoked
     */
    private var successCallback: (() -> Unit)? = null

    /*
     * The rules list to check for validation
     */
    var rulesList = ArrayList<BaseRule<D>>()

    /*
     * Performs the validation check and returns true or false.
     * Also invokes success and error callbacks if non null.
     */
    fun check(): Boolean {
        for (rule in rulesList) {
            if (!rule.validate(text)) {
                setError(rule.getErrorMessage())
                break
            }
        }

        // Invoking callbacks
        if (isValid)
            successCallback?.invoke()
        else
            errorCallback?.invoke(errorMessage)

        return isValid
    }

    fun setError(message: D?) {
        isValid = false
        errorMessage = message
    }

    fun addRule(rule: BaseRule<D>): Validator<D> {
        rulesList.add(rule)
        return this
    }

    fun addErrorCallback(callback: (message: D?) -> Unit): Validator<D> {
        errorCallback = callback
        return this
    }

    fun addSuccessCallback(callback: () -> Unit): Validator<D> {
        successCallback = callback
        return this
    }

    // Rules
    fun nonEmpty(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { NonEmptyRule(errorMsg) } ?: NonEmptyRule()
        addRule(rule)
        return this
    }

    fun minLength(length: Int, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { MinLengthRule(length, errorMsg) } ?: MinLengthRule(length)
        addRule(rule)
        return this
    }

    fun maxLength(length: Int, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { MaxLengthRule(length, errorMsg) } ?: MaxLengthRule(length)
        addRule(rule)
        return this
    }

    fun validEmail(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { EmailRule(errorMsg) } ?: EmailRule()
        addRule(rule)
        return this
    }

    fun validNumber(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { ValidNumberRule(errorMsg) } ?: ValidNumberRule()
        addRule(rule)
        return this
    }

    fun greaterThan(number: Number, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { GreaterThanRule(number, errorMsg) } ?: GreaterThanRule(number)
        addRule(rule)
        return this
    }

    fun greaterThanOrEqual(number: Number, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { GreaterThanOrEqualRule(number, errorMsg) } ?: GreaterThanOrEqualRule(number)
        addRule(rule)
        return this
    }

    fun lessThan(number: Number, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { LessThanRule(number, errorMsg) } ?: LessThanRule(number)
        addRule(rule)
        return this
    }

    fun lessThanOrEqual(number: Number, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { LessThanOrEqualRule(number, errorMsg) } ?: LessThanOrEqualRule(number)
        addRule(rule)
        return this
    }

    fun numberEqualTo(number: Number, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { NumberEqualToRule(number, errorMsg) } ?: NumberEqualToRule(number)
        addRule(rule)
        return this
    }

    fun allLowerCase(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { AllLowerCaseRule(errorMsg) } ?: AllLowerCaseRule()
        addRule(rule)
        return this
    }

    fun allUpperCase(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { AllUpperCaseRule(errorMsg) } ?: AllUpperCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneUpperCase(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { AtLeastOneUpperCaseRule(errorMsg) } ?: AtLeastOneUpperCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneLowerCase(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { AtLeastOneLowerCaseRule(errorMsg) } ?: AtLeastOneLowerCaseRule()
        addRule(rule)
        return this
    }

    fun atleastOneNumber(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { AtLeastOneNumberCaseRule(errorMsg) } ?: AtLeastOneNumberCaseRule()
        addRule(rule)
        return this
    }

    fun noNumbers(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { NoNumbersRule(errorMsg) } ?: NoNumbersRule()
        addRule(rule)
        return this
    }

    fun onlyNumbers(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { OnlyNumbersRule(errorMsg) } ?: OnlyNumbersRule()
        addRule(rule)
        return this
    }

    fun startWithNumber(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { StartsWithNumberRule(errorMsg) } ?: StartsWithNumberRule()
        addRule(rule)
        return this
    }

    fun startWithNonNumber(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { StartsWithNoNumberRule(errorMsg) } ?: StartsWithNoNumberRule()
        addRule(rule)
        return this
    }

    fun noSpecialCharacters(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { NoSpecialCharacterRule(errorMsg) } ?: NoSpecialCharacterRule()
        addRule(rule)
        return this
    }

    fun atleastOneSpecialCharacters(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { AtLeastOneSpecialCharacterRule(errorMsg) } ?: AtLeastOneSpecialCharacterRule()
        addRule(rule)
        return this
    }

    fun textEqualTo(target: String, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { TextEqualToRule(target, errorMsg) } ?: TextEqualToRule(target)
        addRule(rule)
        return this
    }

    fun textNotEqualTo(target: String, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { TextNotEqualToRule(target, errorMsg) } ?: TextNotEqualToRule(target)
        addRule(rule)
        return this
    }

    fun startsWith(target: String, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { StartsWithRule(target, errorMsg) } ?: StartsWithRule(target)
        addRule(rule)
        return this
    }

    fun endsWith(target: String, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { EndsWithRule(target, errorMsg) } ?: EndsWithRule(target)
        addRule(rule)
        return this
    }

    fun contains(target: String, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { ContainsRule(target, errorMsg) } ?: ContainsRule(target)
        addRule(rule)
        return this
    }

    fun notContains(target: String, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { NotContainsRule(target, errorMsg) } ?: NotContainsRule(target)
        addRule(rule)
        return this
    }

    fun creditCardNumber(
        creditCardErrorMsg: D? = null, minLengthErrorMsg: D? = null, maxLengthErrorMsg: D? = null
    ): Validator<D> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg) } ?: MinLengthRule(16)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg) } ?: MaxLengthRule(16)
        val creditCardRule = creditCardErrorMsg?.let { CreditCardRule(creditCardErrorMsg) } ?: CreditCardRule()

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun creditCardNumberWithSpaces(
        creditCardErrorMsg: D? = null, minLengthErrorMsg: D? = null, maxLengthErrorMsg: D? = null
    ): Validator<D> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg) } ?: MinLengthRule(19)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg) } ?: MaxLengthRule(19)
        val creditCardRule =
            creditCardErrorMsg?.let { CreditCardWithSpacesRule(creditCardErrorMsg) } ?: CreditCardWithSpacesRule()

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun creditCardNumberWithDashes(
        creditCardErrorMsg: D? = null, minLengthErrorMsg: D? = null, maxLengthErrorMsg: D? = null
    ): Validator<D> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg) } ?: MinLengthRule(19)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg) } ?: MaxLengthRule(19)
        val creditCardRule =
            creditCardErrorMsg?.let { CreditCardWithDashesRule(creditCardErrorMsg) } ?: CreditCardWithDashesRule()

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun validUrl(errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { ValidUrlRule(errorMsg) } ?: ValidUrlRule()
        addRule(rule)
        return this
    }

    fun regex(pattern: String, errorMsg: D? = null): Validator<D> {
        val rule = errorMsg?.let { RegexRule(pattern, errorMsg) } ?: RegexRule(pattern)
        addRule(rule)
        return this
    }
}