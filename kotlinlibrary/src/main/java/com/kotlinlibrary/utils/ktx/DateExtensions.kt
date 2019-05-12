package com.kotlinlibrary.utils.ktx

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

// Converts current date to Calendar
fun Date.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal
}

fun Date.isFuture(): Boolean {
    return !Date().before(this)
}

fun Date.isPast(): Boolean {
    return Date().before(this)
}

fun Date.isToday(): Boolean {
    return DateUtils.isToday(this.time)
}

fun Date.isYesterday(): Boolean {
    return DateUtils.isToday(this.time + DateUtils.DAY_IN_MILLIS)
}

fun Date.isTomorrow(): Boolean {
    return DateUtils.isToday(this.time - DateUtils.DAY_IN_MILLIS)
}

fun Date.today(): Date {
    return Date()
}

fun Date.yesterday(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, -1)
    return cal.time
}

fun Date.tomorrow(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    return cal.time
}

fun Date.hour(): Int {
    return this.toCalendar().get(Calendar.HOUR)
}

fun Date.minute(): Int {
    return this.toCalendar().get(Calendar.MINUTE)
}

fun Date.second(): Int {
    return this.toCalendar().get(Calendar.SECOND)
}

fun Date.month(): Int {
    return this.toCalendar().get(Calendar.MONTH) + 1
}

fun Date.monthName(locale: Locale? = Locale.getDefault()): String {
    return this.toCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, locale)
}

fun Date.year(): Int {
    return this.toCalendar().get(Calendar.YEAR)
}

fun Date.day(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_MONTH)
}

fun Date.dayOfWeek(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_WEEK)
}

fun Date.dayOfWeekName(locale: Locale? = Locale.getDefault()): String {
    return this.toCalendar().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale)
}

fun Date.dayOfYear(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_YEAR)
}
