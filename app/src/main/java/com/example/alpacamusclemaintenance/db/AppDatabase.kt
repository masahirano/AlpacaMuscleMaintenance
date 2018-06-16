package com.example.alpacamusclemaintenance.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.db.entity.PushUp

@Database(
        entities = [
            PushUp::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pushUpDao(): PushUpDao
}