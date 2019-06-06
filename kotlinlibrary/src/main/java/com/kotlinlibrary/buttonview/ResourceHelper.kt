package com.kotlinlibrary.buttonview

import android.content.Context
import android.content.res.TypedArray
import com.kotlinlibrary.R

import java.lang.reflect.Field
import java.util.ArrayList

object ResourceHelper {
    private fun getMultiTypedArray(context: Context, key: String): List<TypedArray> {
        val array = ArrayList<TypedArray>()

        try {
            val res = R.array::class.java
            var field: Field?
            var counter = 0

            do {
                field = res.getField(key + "_" + counter)
                array.add(context.resources.obtainTypedArray(field!!.getInt(null)))
                counter++
            } while (field != null)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return array
        }
    }

    fun getFloatArray(context: Context, name: String): List<Float> {
        val temp = ArrayList<Float>()
        for (item in ResourceHelper.getMultiTypedArray(context, name)) {
            temp.add(item.getFloat(0, 0f))
        }
        return temp
    }
}