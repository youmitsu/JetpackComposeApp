package com.example.api.di

import com.example.api.MeigenApiService
import com.example.model.Meigen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ApiModule {
    @Provides
    fun provideMeigenApiService(): MeigenApiService {
        return object : MeigenApiService {
            override suspend fun getMeigenList(): List<Meigen> {
                return listOf(
                    Meigen("少年よ大志を抱け", "ギリシャのなんか"),
                    Meigen("吾輩は猫である", "夏目漱石"),
                )
            }
        }
    }
}