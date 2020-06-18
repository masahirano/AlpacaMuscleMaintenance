package com.example.alpacamusclemaintenance.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import io.reactivex.Observable

class RecordViewModel @ViewModelInject constructor(
  private val repository: PushUpRepository
) : ViewModel() {

  val pushUpsObservable: Observable<List<PushUp>>
    get() = repository.getPushUps()
}
