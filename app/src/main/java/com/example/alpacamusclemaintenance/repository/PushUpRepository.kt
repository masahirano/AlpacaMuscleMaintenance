package com.example.alpacamusclemaintenance.repository

import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import javax.inject.Inject

class PushUpRepository @Inject constructor(
  private val pushUpDao: PushUpDao
) {

  fun getPushUps() = pushUpDao.getPushUps()
}
