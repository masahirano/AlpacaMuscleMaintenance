package com.example.alpacamusclemaintenance.repository

import androidx.lifecycle.LiveData
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.db.entity.PushUpEntity
import javax.inject.Inject

class PushUpRepository @Inject constructor(
    private val pushUpDao: PushUpDao
) {

    fun getPushUps(): LiveData<List<PushUpEntity>> = pushUpDao.getPushUps()
}
