package com.example.alpacamusclemaintenance.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.alpacamusclemaintenance.db.Converters
import java.util.*

@Entity(tableName = "push_ups")
@TypeConverters(Converters::class)
data class PushUp(

  @PrimaryKey(autoGenerate = true)
  @NonNull
  val id: Int,

  @NonNull
  val count: Int,

  @NonNull
  @ColumnInfo(name = "done_at")
  val doneAt: Date = Date(),

  @ColumnInfo(name = "created_at")
  val createdAt: Date = Date(),

  @ColumnInfo(name = "updated_at")
  val updatedAt: Date = Date()
)
