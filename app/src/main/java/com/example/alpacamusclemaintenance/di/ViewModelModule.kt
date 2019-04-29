// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/ViewModelModule.kt

package com.example.alpacamusclemaintenance.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.alpacamusclemaintenance.viewmodel.HomeViewModel
import com.example.alpacamusclemaintenance.viewmodel.AlpacaMuscleMaintenanceViewModelFactory
import com.example.alpacamusclemaintenance.viewmodel.PushUpViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PushUpViewModel::class)
    abstract fun bindPushUpViewModel(pushUpViewModel: PushUpViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AlpacaMuscleMaintenanceViewModelFactory): ViewModelProvider.Factory
}
