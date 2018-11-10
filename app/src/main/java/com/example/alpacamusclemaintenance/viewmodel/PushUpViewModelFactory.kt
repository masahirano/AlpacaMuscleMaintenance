package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alpacamusclemaintenance.repository.PushUpRepository

class PushUpViewModelFactory(
        private val repository: PushUpRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = PushUpViewModel(repository) as T

}
