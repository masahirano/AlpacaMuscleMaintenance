package com.example.alpacamusclemaintenance.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alpacamusclemaintenance.vo.Home

class HomeRepository private constructor() {

    fun getHome(): LiveData<Home> {
        val data = MutableLiveData<Home>()
        val words = arrayListOf(
                Home("私の場合、セットが終わるのは、何レップスしたからと言うのではなく、私の筋肉がそのセットの終了を告げる時である。しかし、私の心はトレーニングを止めない。", "R.ロビンソン"),
                Home("共に鍛え、共に喜び、共に笑い、共に泣く、いついかなる時も決して裏切ることのない唯一無二の親友、それが筋肉さ。", "D.ブレーカー")
        )
        data.value = words.shuffled()[0]

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
