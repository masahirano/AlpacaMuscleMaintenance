package com.example.alpacamusclemaintenance.data.di

import com.example.alpacamusclemaintenance.data.feed.FeedRepository
import com.example.alpacamusclemaintenance.data.feed.QiitaApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @[Provides Singleton]
    fun provideFeedRepository(
        service: QiitaApi
    ): FeedRepository = FeedRepository(
        service = service
    )

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
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
    fun provideQiitaApi(
        client: OkHttpClient,
        gson: Gson
    ): QiitaApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(QiitaApi.HTTPS_API_QIITA_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(QiitaApi::class.java)
    }
}
