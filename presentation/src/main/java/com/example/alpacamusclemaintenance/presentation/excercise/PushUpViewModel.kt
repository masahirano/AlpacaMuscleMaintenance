package com.example.alpacamusclemaintenance.presentation.excercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alpacamusclemaintenance.data.pushup.PushUpRepository
import com.example.alpacamusclemaintenance.domain.pushup.PushUp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PushUpViewModel @Inject constructor(
    private val pushUpRepository: PushUpRepository
) : ViewModel() {

    val count: MutableLiveData<Int> = MutableLiveData(DEFAULT_VALUE)

    fun add(addValue: Int) {
        (count.value ?: 0).also { currentValue ->
            count.value = currentValue + addValue
        }
    }

    fun save() {
        val value = count.value ?: return
        viewModelScope.launch {
            val now = Date()
            pushUpRepository.insert(
                PushUp(
                    id = 0,
                    count = value,
                    doneAt = now,
                    createdAt = now,
                    updatedAt = now
                )
            )
            count.value = DEFAULT_VALUE
        }
    }

    companion object {

        private const val DEFAULT_VALUE = 0
    }
}
