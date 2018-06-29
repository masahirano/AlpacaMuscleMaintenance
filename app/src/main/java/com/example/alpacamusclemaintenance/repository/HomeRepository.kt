package com.example.alpacamusclemaintenance.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.alpacamusclemaintenance.vo.Home

class HomeRepository private constructor() {

    fun getHome(): LiveData<Home> {
        val data = MutableLiveData<Home>()
        data.value = Home("私の場合、セットが終わるのは、何レップスしたからと言うのではなく \n" +
                "私の筋肉がそのセットの終了を告げる時である。 \n" +
                "しかし、私の心はトレーニングを止めない。 ", "R.ロビンソン")

        return data
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: HomeRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: HomeRepository().also { instance = it }
                }
    }

}
