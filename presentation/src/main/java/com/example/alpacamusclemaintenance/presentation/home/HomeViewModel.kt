package com.example.alpacamusclemaintenance.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.domain.home.Home
import com.example.alpacamusclemaintenance.domain.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val data: LiveData<Home>
        get() = repository.getHome()
}
