package com.example.alpacamusclemaintenance.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.alpacamusclemaintenance.repository.FeedRepository

class FeedViewModelFactory(
        private val repository: FeedRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = FeedViewModel(repository) as T

}