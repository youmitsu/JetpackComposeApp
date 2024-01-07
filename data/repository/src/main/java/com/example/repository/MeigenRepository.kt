package com.example.repository

import com.example.api.MeigenApiService
import com.example.local.dao.MeigenDao
import com.example.local.entity.toEntity
import com.example.local.entity.toModel
import com.example.model.Meigen
import java.util.UUID
import javax.inject.Inject

interface MeigenRepository {
    fun createId(): String
    suspend fun getAll(): List<Meigen>
    suspend fun save(meigen: Meigen): Unit
}

class MeigenRepositoryImpl @Inject constructor(
    private val meigenApiService: MeigenApiService,
    private val meigenDao: MeigenDao,
) : MeigenRepository {
    override fun createId(): String {
        return UUID.randomUUID().toString()
    }

    override suspend fun getAll(): List<Meigen> {
        val meigens = meigenDao.getAll()
        return meigens.map {
            it.toModel()
        }
    }

    override suspend fun save(meigen: Meigen) {
        val serialized = meigen.toEntity()
        meigenDao.insertAll(serialized)
    }
}