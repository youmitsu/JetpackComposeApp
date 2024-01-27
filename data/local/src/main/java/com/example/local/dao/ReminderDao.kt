package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.local.entity.Reminder

@Dao
interface ReminderDao {
    @Insert
    fun insertAll(vararg reminders: Reminder)
}