package com.kotlinlibrary.validation

import com.kotlinlibrary.validation.rules.BaseRule
import com.kotlinlibrary.validation.rules.*

/**
 * The core Validator builder class for validation operations and checks!
 * This class allows developers to process single or multiple validation checks on input views.
 */
class Validator<ErrorMessage>(
    val value: String,
    private val clazz: Class<ErrorMessage>
) {
    /*
     * Boolean to determine whether all the validations have passed successfully!
     * If any validation check is failed, then the value to
     * false and result is returned to developer
     */
    private var isValid = true

    /*
     The error message to be sent in the error callback
     */
    private var errorMessage: ErrorMessage? = null

    /*
     * In case of validation error or failure, this callback is invoked
     */
    private var errorCallback: ((message: ErrorMessage?) -> Unit)? = null

    /*
     * In case of validation success, this callback is invoked
     */
    private var successCallback: (() -> Unit)? = null

    /*
     * The rules list to check for validation
     */
    var rulesList = ArrayList<BaseRule<ErrorMessage>>()

    /*
     * Performs the validation check and returns true or false.
     * Also invokes success and error callbacks if non null.
     */
    fun check(): Boolean {
        for (rule in rulesList) {
            if (!rule.validate(value)) {
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

    fun setError(message: ErrorMessage?) {
        isValid = false
        errorMessage = message
    }

    fun addRule(rule: BaseRule<ErrorMessage>): Validator<ErrorMessage> {
        rulesList.add(rule)
        return this
    }

    fun addErrorCallback(callback: (message: ErrorMessage?) -> Unit): Validator<ErrorMessage> {
        errorCallback = callback
        return this
    }

    fun addSuccessCallback(callback: () -> Unit): Validator<ErrorMessage> {
        successCallback = callback
        return this
    }

    // Rules
    fun nonEmpty(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { NonEmptyRule(errorMsg, clazz) } ?: NonEmptyRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun minLength(length: Int, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { MinLengthRule(length, errorMsg, clazz) } ?: MinLengthRule(length, clazz = clazz)
        addRule(rule)
        return this
    }

    fun maxLength(length: Int, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { MaxLengthRule(length, errorMsg, clazz) } ?: MaxLengthRule(length, clazz = clazz)
        addRule(rule)
        return this
    }

    fun validEmail(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { EmailRule(errorMsg, clazz) } ?: EmailRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun validNumber(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { ValidNumberRule(errorMsg, clazz) } ?: ValidNumberRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun greaterThan(number: Number, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { GreaterThanRule(number, errorMsg, clazz) } ?: GreaterThanRule(number, clazz = clazz)
        addRule(rule)
        return this
    }

    fun greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { GreaterThanOrEqualRule(number, errorMsg, clazz) } ?: GreaterThanOrEqualRule(number, clazz = clazz)
        addRule(rule)
        return this
    }

    fun lessThan(number: Number, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { LessThanRule(number, errorMsg, clazz) } ?: LessThanRule(number, clazz = clazz)
        addRule(rule)
        return this
    }

    fun lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { LessThanOrEqualRule(number, errorMsg, clazz) } ?: LessThanOrEqualRule(number, clazz = clazz)
        addRule(rule)
        return this
    }

    fun numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { NumberEqualToRule(number, errorMsg, clazz) } ?: NumberEqualToRule(number, clazz = clazz)
        addRule(rule)
        return this
    }

    fun allLowerCase(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { AllLowerCaseRule(errorMsg, clazz) } ?: AllLowerCaseRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun allUpperCase(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { AllUpperCaseRule(errorMsg, clazz) } ?: AllUpperCaseRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun atleastOneUpperCase(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { AtLeastOneUpperCaseRule(errorMsg, clazz) } ?: AtLeastOneUpperCaseRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun atleastOneLowerCase(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { AtLeastOneLowerCaseRule(errorMsg, clazz) } ?: AtLeastOneLowerCaseRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun atleastOneNumber(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { AtLeastOneNumberCaseRule(errorMsg, clazz) } ?: AtLeastOneNumberCaseRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun noNumbers(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { NoNumbersRule(errorMsg, clazz) } ?: NoNumbersRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun onlyNumbers(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { OnlyNumbersRule(errorMsg, clazz) } ?: OnlyNumbersRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun startWithNumber(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { StartsWithNumberRule(errorMsg, clazz) } ?: StartsWithNumberRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun startWithNonNumber(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { StartsWithNoNumberRule(errorMsg, clazz) } ?: StartsWithNoNumberRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun noSpecialCharacters(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { NoSpecialCharacterRule(errorMsg, clazz) } ?: NoSpecialCharacterRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { AtLeastOneSpecialCharacterRule(errorMsg, clazz) } ?: AtLeastOneSpecialCharacterRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun textEqualTo(target: String, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { TextEqualToRule(target, errorMsg, clazz) } ?: TextEqualToRule(target, clazz = clazz)
        addRule(rule)
        return this
    }

    fun textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { TextNotEqualToRule(target, errorMsg, clazz) } ?: TextNotEqualToRule(target, clazz = clazz)
        addRule(rule)
        return this
    }

    fun startsWith(target: String, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { StartsWithRule(target, errorMsg, clazz) } ?: StartsWithRule(target, clazz = clazz)
        addRule(rule)
        return this
    }

    fun endsWith(target: String, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { EndsWithRule(target, errorMsg, clazz) } ?: EndsWithRule(target, clazz = clazz)
        addRule(rule)
        return this
    }

    fun contains(target: String, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { ContainsRule(target, errorMsg, clazz) } ?: ContainsRule(target, clazz = clazz)
        addRule(rule)
        return this
    }

    fun notContains(target: String, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { NotContainsRule(target, errorMsg, clazz) } ?: NotContainsRule(target, clazz = clazz)
        addRule(rule)
        return this
    }

    fun creditCardNumber(
        creditCardErrorMsg: ErrorMessage? = null, minLengthErrorMsg: ErrorMessage? = null, maxLengthErrorMsg: ErrorMessage? = null
    ): Validator<ErrorMessage> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg, clazz) } ?: MinLengthRule(16, clazz = clazz)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg, clazz) } ?: MaxLengthRule(16, clazz = clazz)
        val creditCardRule = creditCardErrorMsg?.let { CreditCardRule(creditCardErrorMsg, clazz) } ?: CreditCardRule(clazz = clazz)

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun creditCardNumberWithSpaces(
        creditCardErrorMsg: ErrorMessage? = null, minLengthErrorMsg: ErrorMessage? = null, maxLengthErrorMsg: ErrorMessage? = null
    ): Validator<ErrorMessage> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg, clazz) } ?: MinLengthRule(19, clazz = clazz)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg, clazz) } ?: MaxLengthRule(19, clazz = clazz)
        val creditCardRule =
            creditCardErrorMsg?.let { CreditCardWithSpacesRule(creditCardErrorMsg, clazz) } ?: CreditCardWithSpacesRule(clazz = clazz)

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun creditCardNumberWithDashes(
        creditCardErrorMsg: ErrorMessage? = null, minLengthErrorMsg: ErrorMessage? = null, maxLengthErrorMsg: ErrorMessage? = null
    ): Validator<ErrorMessage> {
        val minLengthRule = minLengthErrorMsg?.let { MinLengthRule(16, minLengthErrorMsg, clazz) } ?: MinLengthRule(19, clazz = clazz)
        val maxLengthRule = maxLengthErrorMsg?.let { MaxLengthRule(16, maxLengthErrorMsg, clazz) } ?: MaxLengthRule(19, clazz = clazz)
        val creditCardRule =
            creditCardErrorMsg?.let { CreditCardWithDashesRule(creditCardErrorMsg, clazz) } ?: CreditCardWithDashesRule(clazz = clazz)

        addRule(minLengthRule)
        addRule(maxLengthRule)
        addRule(creditCardRule)
        return this
    }

    fun validUrl(errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { ValidUrlRule(errorMsg, clazz) } ?: ValidUrlRule(clazz = clazz)
        addRule(rule)
        return this
    }

    fun regex(pattern: String, errorMsg: ErrorMessage? = null): Validator<ErrorMessage> {
        val rule = errorMsg?.let { RegexRule(pattern, errorMsg, clazz) } ?: RegexRule(pattern, clazz = clazz)
        addRule(rule)
        return this
    }
}