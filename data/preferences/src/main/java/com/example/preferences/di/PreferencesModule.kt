package com.example.preferences.di

import com.example.preferences.LaunchPref
import com.example.preferences.LaunchPrefImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {
    @Binds
    abstract fun bindLaunchPref(impl: LaunchPrefImpl): LaunchPref
}