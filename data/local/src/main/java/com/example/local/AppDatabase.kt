package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.dao.MeigenDao
import com.example.local.dao.ReminderDao
import com.example.local.entity.Meigen
import com.example.local.entity.Reminder

@Database(entities = [Meigen::class, Reminder::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun meigenDao(): MeigenDao
    abstract fun reminderDao(): ReminderDao
}
