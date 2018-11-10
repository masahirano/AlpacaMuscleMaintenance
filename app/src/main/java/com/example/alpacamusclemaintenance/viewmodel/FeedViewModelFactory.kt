package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alpacamusclemaintenance.repository.FeedRepository

class FeedViewModelFactory(
        private val repository: FeedRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = FeedViewModel(repository) as T

}