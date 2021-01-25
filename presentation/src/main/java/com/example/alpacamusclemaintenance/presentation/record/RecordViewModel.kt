package com.example.alpacamusclemaintenance.presentation.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.domain.pushup.PushUp
import com.example.alpacamusclemaintenance.domain.pushup.PushUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val repository: PushUpRepository
) : ViewModel() {

    val pushUpsObservableEntity: LiveData<List<PushUp>>
        get() = repository.getPushUps()
}
