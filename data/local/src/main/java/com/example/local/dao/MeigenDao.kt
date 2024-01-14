package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.local.entity.Meigen

@Dao
interface MeigenDao {
    @Insert
    fun insertAll(vararg maigens: Meigen)

    @Query("SELECT * FROM meigens ORDER BY createdAt DESC")
    suspend fun getAll(): List<Meigen>
}
