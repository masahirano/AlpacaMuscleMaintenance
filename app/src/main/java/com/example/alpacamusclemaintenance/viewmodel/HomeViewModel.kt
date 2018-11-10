package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.HomeRepository
import com.example.alpacamusclemaintenance.vo.Home

class HomeViewModel internal constructor(
        private val repository: HomeRepository
): ViewModel() {

    var homeObservable: LiveData<Home> = repository.getHome()

}
