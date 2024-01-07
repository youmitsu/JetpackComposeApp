package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.dao.MeigenDao
import com.example.local.entity.Meigen

@Database(entities = [Meigen::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun meigenDao(): MeigenDao
}
