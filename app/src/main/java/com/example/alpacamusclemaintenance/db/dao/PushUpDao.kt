package com.example.alpacamusclemaintenance.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alpacamusclemaintenance.db.entity.PushUp

@Dao
interface PushUpDao {
    @Insert
    fun insert(pushUp: PushUp)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pushUps: List<PushUp>)

    @Query("SELECT * from push_ups ORDER BY done_at DESC LIMIT 10")
    fun getPushUps(): LiveData<List<PushUp>>

    @Query("DELETE FROM push_ups")
    fun deleteAll()
}