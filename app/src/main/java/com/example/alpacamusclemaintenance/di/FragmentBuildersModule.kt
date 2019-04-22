package com.example.alpacamusclemaintenance.di

import com.example.alpacamusclemaintenance.ui.HomeFragment
//import com.android.example.github.ui.repo.RepoFragment
//import com.android.example.github.ui.search.SearchFragment
//import com.android.example.github.ui.user.UserFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

//    @ContributesAndroidInjector
//    abstract fun contributeRepoFragment(): RepoFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeUserFragment(): UserFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeSearchFragment(): SearchFragment
}
