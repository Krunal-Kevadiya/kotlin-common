package com.kotlinlibrary.recycleradapter

/**
 * DslMarker for pipeline execution context
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class ContextDsl