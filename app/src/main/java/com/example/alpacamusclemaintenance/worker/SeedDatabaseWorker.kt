package com.example.alpacamusclemaintenance.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.entity.PushUpEntity
import com.example.alpacamusclemaintenance.util.PUSH_UP_DATA_FILENAME
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    private val TAG = SeedDatabaseWorker::class.java.simpleName

    override fun doWork(): Result {
        val pushUpType = object : TypeToken<List<PushUpEntity>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open(PUSH_UP_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val gson = GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create()
            val pushUpEntityList: List<PushUpEntity> = gson.fromJson(jsonReader, pushUpType)
            val database = AppDatabase.getInstance(applicationContext)
            database.pushUpDao().insertAll(pushUpEntityList)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        } finally {
            jsonReader?.close()
        }
    }
}
