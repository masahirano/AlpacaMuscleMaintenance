// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/AppModule.kt

package com.example.alpacamusclemaintenance.di

import android.app.Application
import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

  @[Provides Singleton]
  fun provideAppDatabase(app: Application): AppDatabase =
    AppDatabase.getInstance(app.applicationContext)

  @[Provides Singleton]
  fun providePushUpDao(db: AppDatabase): PushUpDao =
    db.pushUpDao()

  @[Provides Singleton]
  fun provideQiitaService(): QiitaService {
    val gson = GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create()
    val retrofit = Retrofit
      .Builder()
      .baseUrl(QiitaService.HTTPS_API_QIITA_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()

    return retrofit.create(QiitaService::class.java)
  }
}
