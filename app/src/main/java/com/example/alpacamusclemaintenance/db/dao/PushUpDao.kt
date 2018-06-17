package com.example.alpacamusclemaintenance.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.alpacamusclemaintenance.db.entity.PushUp

@Dao
interface PushUpDao {
    @Insert
    fun insert(pushUp: PushUp)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pushUps: List<PushUp>)

    @Query("SELECT * from push_ups ORDER BY id ASC")
    fun getPushUps(): LiveData<List<PushUp>>

    @Query("DELETE FROM push_ups")
    fun deleteAll()
}