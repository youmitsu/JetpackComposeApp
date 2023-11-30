package com.example.repository.di

import com.example.repository.MeigenRepository
import com.example.repository.MeigenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMeigenRepository(impl: MeigenRepositoryImpl): MeigenRepository
}