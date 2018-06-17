package com.example.alpacamusclemaintenance.repository

import com.example.alpacamusclemaintenance.db.dao.PushUpDao

class PushUpRepository private constructor(private val pushUpDao: PushUpDao) {

    fun getPushUps() = pushUpDao.getPushUps()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PushUpRepository? = null

        fun getInstance(pushUpDao: PushUpDao) =
                instance ?: synchronized(this) {
                    instance ?: PushUpRepository(pushUpDao).also { instance = it }
                }
    }
}