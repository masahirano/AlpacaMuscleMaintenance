package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import javax.inject.Inject

class PushUpViewModel @Inject constructor(
  private val repository: PushUpRepository
) : ViewModel() {

  val pushUpsObservable: LiveData<List<PushUp>>
    get() = repository.getPushUps()
}
