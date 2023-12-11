package com.example.repository

import com.example.api.MeigenApiService
import com.example.model.Meigen
import kotlinx.coroutines.delay
import javax.inject.Inject

interface MeigenRepository {
    suspend fun getAll(): List<Meigen>
    suspend fun save(meigen: Meigen): Unit
}

class MeigenRepositoryImpl @Inject constructor(
    private val meigenApiService: MeigenApiService
) : MeigenRepository {
    override suspend fun getAll(): List<Meigen> {
        return meigenApiService.getMeigenList()
    }

    override suspend fun save(meigen: Meigen) {
        delay(500)
    }
}