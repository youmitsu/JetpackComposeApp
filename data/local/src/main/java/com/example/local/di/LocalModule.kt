package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "meigen-database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMeigenDao(db: AppDatabase) = db.meigenDao()
}