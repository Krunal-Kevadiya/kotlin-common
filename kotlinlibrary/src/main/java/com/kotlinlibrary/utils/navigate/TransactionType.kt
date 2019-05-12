package com.kotlinlibrary.utils.navigate

/*enum class TransactionType {
    ADD, REPLACE
}*/

sealed class TransactionType {
    open class FragmentType: TransactionType() {
        object Add : FragmentType()
        object Replace : FragmentType()
    }
    open class DialogFragmentType(): TransactionType() {
        object Show : DialogFragmentType()
        object Hide : DialogFragmentType()
    }
}
