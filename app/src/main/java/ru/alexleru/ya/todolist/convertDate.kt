package ru.alexleru.ya.todolist

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun stringFromDatePickerToStringFormat(dateString: String): String {
    val sdfParse = SimpleDateFormat("dd/MM/yyyy")
    val date = sdfParse.parse(dateString)
    val sdfFormat = SimpleDateFormat("dd MMMM yyyy")
    return date?.let { sdfFormat.format(it) } ?: "Error"
}

fun String?.stringFromDatePickerToDateFormat(): Date? {
    val sdfParse = SimpleDateFormat("dd MMMM yyyy")
    return this?.let { sdfParse.parse(it) }
}

fun dateToTriple(date: Date): Triple<Int, Int, Int> {
    return Triple(
        getPartOfDateToInt("yyyy", date),
        getPartOfDateToInt("M", date) - 1,
        getPartOfDateToInt("d", date)
    )
}

fun getPartOfDateToInt(part: String, date: Date) = DateFormat.format(part, date).toString().toInt()

fun Date.toStringDateForList(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(this)
}

fun Date.toStringDateForForm(): String {
    val dateFormat = SimpleDateFormat("dd MMMM yyyy")
    return dateFormat.format(this)
}

fun String.toDate(): Date {
    return SimpleDateFormat("dd/MM/yyyy").parse(this)
}

fun String.toDateTime(): Date {
    return SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(this)
}


