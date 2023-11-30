package com.example.repository

import com.example.api.MeigenApiService
import com.example.model.Meigen
import javax.inject.Inject

interface MeigenRepository {
    suspend fun getAll(): List<Meigen>
}

class MeigenRepositoryImpl @Inject constructor(
    private val meigenApiService: MeigenApiService
) : MeigenRepository {
    override suspend fun getAll(): List<Meigen> {
        return meigenApiService.getMeigenList()
    }
}