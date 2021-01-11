package com.example.alpacamusclemaintenance.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.HomeRepository
import com.example.alpacamusclemaintenance.vo.Home

class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val data: LiveData<Home>
        get() = repository.getHome()
}
