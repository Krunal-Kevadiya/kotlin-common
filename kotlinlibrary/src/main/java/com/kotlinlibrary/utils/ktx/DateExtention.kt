package com.kotlinlibrary.utils.ktx

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.asDateString(format :String? = "yyyy-MM-dd HH:mm:ss") :String = SimpleDateFormat(format, Locale.getDefault()).format(this)

fun Long.asDateString(format :String? = "yyyy-MM-dd HH:mm:ss") :String = Date(this).asDateString(format)

fun String.asDateDate(format :String? = "yyyy-MM-dd HH:mm:ss") :Date? = try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this)
} catch(e :Exception) {
    logs(e)
    null
}

fun String.asDateLong(format :String? = "yyyy-MM-dd HH:mm:ss") :Long? = try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this).time
} catch(e :Exception) {
    logs(e)
    null
}

fun Date.toDateString(fromFormat :String, toFormat :String) :String? {
    val df = SimpleDateFormat(fromFormat, Locale.getDefault())
    val df2 = SimpleDateFormat(toFormat, Locale.getDefault())
    return try {
        df2.format(df.format(this))
    } catch(e :ParseException) {
        logs(e)
        null
    }
}

fun Long.toDateString(fromFormat :String, toFormat :String) :String? {
    val df = SimpleDateFormat(toFormat, Locale.getDefault())
    return try {
        df.format(this.asDateString(fromFormat))
    } catch(e :ParseException) {
        logs(e)
        null
    }
}

