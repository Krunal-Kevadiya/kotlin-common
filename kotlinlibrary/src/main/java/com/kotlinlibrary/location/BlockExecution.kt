package com.kotlinlibrary.location

/**
 * handles failure execution calls
 * */
class BlockExecution internal constructor() {

    private var failureFunc: Throwable.() -> Unit = {}

    /**
     * provides block to be executed when there's a failure
     * */
    infix fun failure(func: Throwable.() -> Unit) {
        this.failureFunc = func
    }

    /**
     * invokes failure function
     * */
    internal operator fun invoke(throwable: Throwable) {
        failureFunc(throwable)
    }
}