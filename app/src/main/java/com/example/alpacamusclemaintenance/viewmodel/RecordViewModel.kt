package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.db.entity.PushUpEntity
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val repository: PushUpRepository
) : ViewModel() {

    val pushUpsObservableEntity: LiveData<List<PushUpEntity>>
        get() = repository.getPushUps()
}
