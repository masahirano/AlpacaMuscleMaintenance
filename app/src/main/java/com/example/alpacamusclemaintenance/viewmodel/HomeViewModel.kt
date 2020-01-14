package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.HomeRepository
import com.example.alpacamusclemaintenance.vo.Home
import io.reactivex.Maybe
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val repository: HomeRepository
) : ViewModel() {

  val data: Maybe<Home>
    get() = repository.getHome()
}
