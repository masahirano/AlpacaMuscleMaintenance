package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.HomeRepository
import com.example.alpacamusclemaintenance.vo.Home
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val repository: HomeRepository
) : ViewModel() {

  val homeObservable: LiveData<Home>
    get() = repository.getHome()
}
