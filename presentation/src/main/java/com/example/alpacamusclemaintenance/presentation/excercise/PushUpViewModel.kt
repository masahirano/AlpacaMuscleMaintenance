package com.example.alpacamusclemaintenance.presentation.excercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alpacamusclemaintenance.domain.pushup.InsertPushUpUseCase
import com.example.alpacamusclemaintenance.domain.pushup.PushUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PushUpViewModel @Inject constructor(
    private val insertPushUpUseCase: InsertPushUpUseCase
) : ViewModel() {

    fun save(value: Int) {
        if (value <= 0) return

        viewModelScope.launch {
            val now = Date()
            insertPushUpUseCase(
                PushUp(
                    id = 0,
                    count = value,
                    doneAt = now,
                    createdAt = now,
                    updatedAt = now
                )
            )
        }
    }
}
