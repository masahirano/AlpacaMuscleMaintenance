package com.example.alpacamusclemaintenance.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.db.dao.toPushUpEntity
import com.example.alpacamusclemaintenance.db.dao.toPushUps
import com.example.alpacamusclemaintenance.domain.pushup.PushUp
import com.example.alpacamusclemaintenance.domain.pushup.PushUpRepository
import javax.inject.Inject

class PushUpRepositoryImpl @Inject constructor(
    private val pushUpDao: PushUpDao
) : PushUpRepository {

    override fun getPushUps(): LiveData<List<PushUp>> = pushUpDao
        .getPushUps()
        .map { it.toPushUps() }

    override suspend fun insert(pushUp: PushUp) {
        pushUpDao.insert(pushUp.toPushUpEntity())
    }
}
