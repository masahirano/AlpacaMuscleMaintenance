package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import com.example.alpacamusclemaintenance.vo.PushUp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val repository: PushUpRepository
) : ViewModel() {

    val pushUpsObservableEntity: LiveData<List<PushUp>>
        get() = repository.getPushUps()
}
