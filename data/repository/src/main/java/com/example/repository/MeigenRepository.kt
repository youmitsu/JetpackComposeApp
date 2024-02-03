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
    suspend fun get(meigenId: String): Meigen
    suspend fun getAll(): List<Meigen>
    suspend fun save(meigen: Meigen)
    suspend fun upsert(meigen: Meigen)
    suspend fun delete(meigenId: String)
}

class MeigenRepositoryImpl @Inject constructor(
    private val meigenApiService: MeigenApiService,
    private val meigenDao: MeigenDao,
) : MeigenRepository {
    override fun createId(): String {
        return UUID.randomUUID().toString()
    }

    override suspend fun get(meigenId: String): Meigen {
        val meigen = meigenDao.findById(meigenId)
        return meigen.toModel()
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

    override suspend fun upsert(meigen: Meigen) {
        val serialized = meigen.toEntity()
        meigenDao.upsert(serialized)
    }

    override suspend fun delete(meigenId: String) {
        meigenDao.deleteById(meigenId)
    }
}