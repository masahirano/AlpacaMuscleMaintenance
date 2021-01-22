package com.example.alpacamusclemaintenance.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.db.dao.toPushUps
import com.example.alpacamusclemaintenance.vo.PushUp
import javax.inject.Inject

class PushUpRepository @Inject constructor(
    private val pushUpDao: PushUpDao
) {

    fun getPushUps(): LiveData<List<PushUp>> = pushUpDao.getPushUps().map { it.toPushUps() }
}
