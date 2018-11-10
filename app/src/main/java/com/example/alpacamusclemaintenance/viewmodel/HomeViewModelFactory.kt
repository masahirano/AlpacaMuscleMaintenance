package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alpacamusclemaintenance.repository.HomeRepository

class HomeViewModelFactory(
        private val repository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = HomeViewModel(repository) as T

}