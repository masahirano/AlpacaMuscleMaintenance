package com.example.alpacamusclemaintenance.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.repository.PushUpRepository

class RecordViewModel @ViewModelInject constructor(
  private val repository: PushUpRepository
) : ViewModel() {

  val pushUpsObservable: LiveData<List<PushUp>>
    get() = repository.getPushUps()
}
