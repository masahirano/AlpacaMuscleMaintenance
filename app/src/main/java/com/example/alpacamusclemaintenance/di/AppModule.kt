// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/AppModule.kt

package com.example.alpacamusclemaintenance.di

import android.app.Application
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

  @[Singleton Provides]
  fun provideAppDatabase(app: Application): AppDatabase {
    return AppDatabase.getInstance(app.applicationContext)
  }

  @[Singleton Provides]
  fun providePushUpDao(db: AppDatabase): PushUpDao {
    return db.pushUpDao()
  }
}
