package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.repository.PushUpRepository

class PushUpViewModel internal constructor(
        private val pushUpRepository: PushUpRepository
) : ViewModel() {

    fun getPushUps(): LiveData<List<PushUp>> {
        return pushUpRepository.getPushUps()
    }
}