package com.example.alpacamusclemaintenance.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//import com.android.example.github.ui.repo.RepoViewModel
//import com.android.example.github.ui.search.SearchViewModel
//import com.android.example.github.ui.user.UserViewModel

import com.example.alpacamusclemaintenance.viewmodel.HomeViewModel
import com.example.alpacamusclemaintenance.di.ViewModelKey
import com.example.alpacamusclemaintenance.viewmodel.AlpacaMuscleMaintenanceViewModelFactory

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

//    @Binds
//    @IntoMap
//    @ViewModelKey(SearchViewModel::class)
//    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(RepoViewModel::class)
//    abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AlpacaMuscleMaintenanceViewModelFactory): ViewModelProvider.Factory
}
