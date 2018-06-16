package com.example.alpacamusclemaintenance.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.util.*

@Entity(tableName = "push_ups")
data class PushUp(
        @PrimaryKey(autoGenerate = true)
        @NonNull
        val id: Int,

        @NonNull
        val count: Int,

        @NonNull
        @ColumnInfo(name = "done_at")
        val doneAt: Date,

        @ColumnInfo(name = "created_at")
        val createdAt: Date,

        @ColumnInfo(name = "updated_at")
        val updatedAt: Date
)
