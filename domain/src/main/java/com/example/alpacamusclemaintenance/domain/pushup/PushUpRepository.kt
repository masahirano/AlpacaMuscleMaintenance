package com.example.alpacamusclemaintenance.domain.pushup

import androidx.lifecycle.LiveData

interface PushUpRepository {

    fun getPushUps(): LiveData<List<PushUp>>

    suspend fun insert(pushUp: PushUp)
}
