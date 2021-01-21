// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/AppModule.kt

package com.example.alpacamusclemaintenance.di

import android.content.Context
import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @[Provides Singleton]
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase
        .getInstance(context)

    @[Provides Singleton]
    fun providePushUpDao(db: AppDatabase): PushUpDao = db.pushUpDao()

    @[Provides Singleton]
    fun provideFeedRepository(
        service: QiitaService,
        @ApplicationContext context: Context
    ): FeedRepository = FeedRepository(
        service = service,
        context = context
    )

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = Level.BASIC
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @[Provides Singleton]
    fun provideQiitaService(
        client: OkHttpClient,
        gson: Gson
    ): QiitaService {
        val retrofit = Retrofit.Builder()
            .baseUrl(QiitaService.HTTPS_API_QIITA_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(QiitaService::class.java)
    }
}
