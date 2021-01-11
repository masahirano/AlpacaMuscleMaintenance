// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/AppModule.kt

package com.example.alpacamusclemaintenance.di

import android.app.Application
import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @[Provides Singleton]
    fun provideAppDatabase(app: Application): AppDatabase =
        AppDatabase.getInstance(app.applicationContext)

    @[Provides Singleton]
    fun providePushUpDao(db: AppDatabase): PushUpDao =
        db.pushUpDao()

    @[Provides Singleton]
    fun provideFeedRepository(
        service: QiitaService,
        app: Application
    ): FeedRepository =
        FeedRepository(
            service = service,
            context = app.applicationContext
        )

    @[Provides Singleton]
    fun provideQiitaService(): QiitaService {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val logger = HttpLoggingInterceptor().apply {
            level = Level.BASIC
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(QiitaService.HTTPS_API_QIITA_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(QiitaService::class.java)
    }
}
