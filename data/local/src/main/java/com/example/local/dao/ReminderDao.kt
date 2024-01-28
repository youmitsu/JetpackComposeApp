package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.local.entity.Reminder

@Dao
interface ReminderDao {
    @Insert
    fun insertAll(vararg reminders: Reminder)

    @Upsert
    fun upsert(reminder: Reminder)

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun findById(id: String): Reminder

    @Query("SELECT * FROM reminders ORDER BY createdAt DESC")
    suspend fun getAll(): List<Reminder>
}