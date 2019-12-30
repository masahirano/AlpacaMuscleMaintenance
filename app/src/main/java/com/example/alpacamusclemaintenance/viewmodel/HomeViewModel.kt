package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.HomeRepository
import com.example.alpacamusclemaintenance.vo.Home
import io.reactivex.Observable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val repository: HomeRepository
) : ViewModel() {

  val homeObservable: Observable<Home>
    get() = repository.getHome()
}
