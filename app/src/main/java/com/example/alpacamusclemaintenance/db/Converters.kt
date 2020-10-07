package com.example.alpacamusclemaintenance.db

import androidx.room.TypeConverter
import java.util.Date
import org.apache.commons.lang3.time.DateFormatUtils

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
