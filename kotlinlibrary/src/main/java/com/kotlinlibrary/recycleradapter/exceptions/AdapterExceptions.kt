package com.kotlinlibrary.recycleradapter.exceptions

/**
 * Common exception
 */
open class AdapterException(message: String, cause: Throwable? = null) : Throwable(message, cause)

/**
 * Is thrown when adapter layout resource id is not defined.
 */
open class UndefinedLayout(message: String) : AdapterException(message)

/**
 * Is thrown when no items are defined in adapter
 */
open class ZeroViewTypes : AdapterException(
    "View types are not defined, at least one must been defined. Use withViewType() method"
)

/**
 * Is thrown when you try to update a undeclared view type. For example when you try to insert a string in a int list.
 */
open class UndeclaredTypeModification(modelType: Class<*>?) : AdapterException(
    "Sorry but $modelType isn't declared as a view type. " +
            "You try to update non-existent view type, you can update only declared view types, " +
            "please declare view type with withViewType() on adapter creation time method before update it"
)

/**
 * Is thrown when you try to update a item with by undeclared tag.
 */
open class UndeclaredTag(tag: String) : AdapterException(
    "There are no view types with tag = $tag. Tag should be explicitly " +
            "declared with dsl method \"withViewType(TAG, configuration)\""
)

/**
 * Is thrown when you try to update items with different items type.
 */
open class WrongTagType(tag: String) : AdapterException(
    "Sorry but $tag is set with different items tyoe or another with " +
            "view type."
)