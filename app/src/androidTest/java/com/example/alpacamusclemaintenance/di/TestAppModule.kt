package com.example.alpacamusclemaintenance.di

import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.mockito.Mockito.mock

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class TestAppModule {

    @Provides
    fun provideAppDatabase(): AppDatabase = mock(AppDatabase::class.java)

    @Provides
    fun providePushUpDao(): PushUpDao = mock(PushUpDao::class.java)
}
