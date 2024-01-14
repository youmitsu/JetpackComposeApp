package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.local.entity.Meigen

@Dao
interface MeigenDao {
    @Insert
    fun insertAll(vararg maigens: Meigen)

    @Upsert
    fun upsert(meigen: Meigen)

    @Query("SELECT * FROM meigens WHERE id = :id")
    suspend fun findById(id: String): Meigen

    @Query("SELECT * FROM meigens ORDER BY createdAt DESC")
    suspend fun getAll(): List<Meigen>

    @Query("DELETE FROM meigens WHERE id = :id")
    suspend fun deleteById(id: String)
}
