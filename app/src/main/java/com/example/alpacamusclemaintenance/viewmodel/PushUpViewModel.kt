package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.entity.PushUp
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class PushUpViewModel @Inject constructor(
  private val database: AppDatabase
) : ViewModel() {

  val count: BehaviorSubject<Int> = BehaviorSubject.createDefault(DEFAULT_VALUE)

  fun add(addValue: Int) {
    count
      .value
      ?.also { count.onNext(it + addValue) }
  }

  fun save() {
    val value = count.value ?: return
    val job = Job()
    CoroutineScope(Dispatchers.IO + job).launch {
      database
        .pushUpDao()
        .insert(
          PushUp(
            id = 0,
            count = value
          )
        )
      count.onNext(DEFAULT_VALUE)
    }
  }

  companion object {

    private const val DEFAULT_VALUE = 0
  }
}
