package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.entity.PushUpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PushUpViewModel @Inject constructor(
    private val database: AppDatabase
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
            database
                .pushUpDao()
                .insert(
                    PushUpEntity(
                        id = 0,
                        count = value
                    )
                )
            count.value = DEFAULT_VALUE
        }
    }

    companion object {

        private const val DEFAULT_VALUE = 0
    }
}
