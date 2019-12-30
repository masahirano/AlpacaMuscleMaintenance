package com.example.alpacamusclemaintenance.repository

import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.db.entity.PushUp
import io.reactivex.Observable
import javax.inject.Inject

class PushUpRepository @Inject constructor(
  private val pushUpDao: PushUpDao
) {

  fun getPushUps(): Observable<List<PushUp>> =
    pushUpDao.getPushUps()
}
