package com.kotlinlibrary.buttonview.utils

data class LinearGradientPojo(
    val angle: Double,
    val colors: IntArray,
    val positions: FloatArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LinearGradientPojo

        if (angle != other.angle) return false
        if (!colors.contentEquals(other.colors)) return false
        if (!positions.contentEquals(other.positions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = angle.hashCode()
        result = 31 * result + colors.contentHashCode()
        result = 31 * result + positions.contentHashCode()
        return result
    }
}