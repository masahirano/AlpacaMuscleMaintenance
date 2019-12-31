package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import io.reactivex.Observable
import javax.inject.Inject

class RecordViewModel @Inject constructor(
  private val repository: PushUpRepository
) : ViewModel() {

  val pushUpsObservable: Observable<List<PushUp>>
    get() = repository.getPushUps()
}
