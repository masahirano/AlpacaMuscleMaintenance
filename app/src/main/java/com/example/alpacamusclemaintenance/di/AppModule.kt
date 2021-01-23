// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/AppModule.kt

package com.example.alpacamusclemaintenance.di

import android.content.Context
import com.example.alpacamusclemaintenance.data.di.DataModule
import com.example.alpacamusclemaintenance.data.pushup.PushUpRepository
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.db.repository.PushUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase
        .getInstance(context)

    @Singleton
    @Provides
    fun providePushUpDao(db: AppDatabase): PushUpDao = db.pushUpDao()
}
