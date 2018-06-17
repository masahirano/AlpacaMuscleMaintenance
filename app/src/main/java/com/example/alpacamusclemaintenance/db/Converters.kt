package com.example.alpacamusclemaintenance.db

import android.arch.persistence.room.TypeConverter
import org.apache.commons.lang3.time.DateUtils
import java.util.*

class Converters {
    @TypeConverter
    fun dateToString(date: Date): String {
      return date.toString()
    }

    @TypeConverter
    fun stringToDate(value: String): Date {
        return DateUtils.parseDate(value, "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss")
    }
}