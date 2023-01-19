package com.example.jettodo.utils

import androidx.room.TypeConverter
import java.util.Date

class Convertor {
    @TypeConverter //Room可以接受Long 所以這邊才用Long轉
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}