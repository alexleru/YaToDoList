package ru.alexleru.ya.todolist.data

import androidx.room.TypeConverter
import java.util.Date

class TypeConvert {
    @TypeConverter
    fun fromDateToLong(date: Date?) = date?.time

    @TypeConverter
    fun fromLongToDate(long: Long?) = long?.let { Date(it) }
}