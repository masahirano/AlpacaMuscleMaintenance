package com.example.alpacamusclemaintenance.db

import android.arch.persistence.room.TypeConverter
import org.apache.commons.lang3.time.DateFormatUtils
import java.util.*

class Converters {
    @TypeConverter
    fun dateToString(date: Date): String {
        return DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(date)
    }

    @TypeConverter
    fun stringToDate(string: String): Date {
        return DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.parse(string)
    }
}