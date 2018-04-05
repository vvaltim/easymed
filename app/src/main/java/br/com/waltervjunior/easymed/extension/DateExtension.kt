package br.com.waltervjunior.easymed.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Walter on 24/03/2018.
 */
fun Date.addMinutes(minutes : Int): Date{
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.MINUTE, minutes)
    return Date(c.timeInMillis)
}

fun Date.addDays(days: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DATE, days)
    return Date(c.timeInMillis)
}

fun Date.addYears(years: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.YEAR, years)
    return Date(c.timeInMillis)
}

fun Date.asString(format: String): String {
    val c = GregorianCalendar()
    c.time = this
    return SimpleDateFormat(format, Locale.getDefault()).format(Date(c.timeInMillis))
}