package com.example.alpacamusclemaintenance.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.repository.PushUpRepository

class PushUpViewModel internal constructor(
        private val pushUpRepository: PushUpRepository
) : ViewModel() {

    fun getPushUps(): LiveData<List<PushUp>> {
        return pushUpRepository.getPushUps()
    }
}