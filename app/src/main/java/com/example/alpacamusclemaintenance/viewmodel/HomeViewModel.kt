package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.data.home.HomeRepository
import com.example.alpacamusclemaintenance.domain.home.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val data: LiveData<Home>
        get() = repository.getHome()
}
