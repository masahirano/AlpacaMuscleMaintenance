package com.example.alpacamusclemaintenance.data.pushup

import androidx.lifecycle.LiveData
import com.example.alpacamusclemaintenance.domain.pushup.PushUp

interface PushUpRepository {

    fun getPushUps(): LiveData<List<PushUp>>

    suspend fun insert(pushUp: PushUp)
}
