package com.example.repository

import android.content.Context
import androidx.room.Room
import com.example.api.MeigenApiService
import com.example.local.AppDatabase
import com.example.local.dao.MeigenDao
import com.example.model.Meigen
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject

interface MeigenRepository {
    suspend fun getAll(): List<Meigen>
    suspend fun save(meigen: Meigen): Unit
}

class MeigenRepositoryImpl @Inject constructor(
    private val meigenApiService: MeigenApiService,
    private val meigenDao: MeigenDao,
) : MeigenRepository {
    override suspend fun getAll(): List<Meigen> {
        return meigenApiService.getMeigenList()
    }

    override suspend fun save(meigen: Meigen) {
        val serialized = com.example.local.entity.Meigen.from(meigen)
        meigenDao.insertAll(serialized)
    }
}