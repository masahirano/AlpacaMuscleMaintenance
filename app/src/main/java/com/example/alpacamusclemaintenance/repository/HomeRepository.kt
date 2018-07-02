package com.example.alpacamusclemaintenance.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.alpacamusclemaintenance.vo.Home

class HomeRepository private constructor() {

    fun getHome(): LiveData<Home> {
        val data = MutableLiveData<Home>()
        val words = arrayOf(
                hashMapOf("word" to "私の場合、セットが終わるのは、何レップスしたからと言うのではなく、私の筋肉がそのセットの終了を告げる時である。しかし、私の心はトレーニングを止めない。", "author" to "R.ロビンソン"),
                hashMapOf("word" to "共に鍛え、共に喜び、共に笑い、共に泣く、いついかなる時も決して裏切ることのない唯一無二の親友、それが筋肉さ。", "author" to "D.ブレーカー")
        )
        val word = words.toList().shuffled()[0]
        data.value = Home(word["word"]!!, word["author"]!!)

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
