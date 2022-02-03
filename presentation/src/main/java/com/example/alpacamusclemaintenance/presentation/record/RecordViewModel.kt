package com.example.alpacamusclemaintenance.presentation.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.domain.pushup.GetPushUpUseCase
import com.example.alpacamusclemaintenance.domain.pushup.PushUp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getPushUpUseCase: GetPushUpUseCase
) : ViewModel() {

    val pushUpsEntities: LiveData<List<PushUp>>
        get() = getPushUpUseCase()
}
