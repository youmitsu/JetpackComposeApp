package com.example.repository

import com.example.local.dao.ReminderDao
import com.example.local.entity.toEntity
import com.example.local.entity.toModel
import com.example.model.Reminder
import java.util.UUID
import javax.inject.Inject

interface ReminderRepository {
    fun createId(): String
    suspend fun get(reminderId: String): Reminder
    suspend fun getAll(): List<Reminder>
    fun save(reminder: Reminder)
    suspend fun upsert(reminder: Reminder)
}

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao,
) : ReminderRepository {
    override fun createId(): String {
        return UUID.randomUUID().toString()
    }

    override suspend fun get(reminderId: String): Reminder {
        val reminder = reminderDao.findById(reminderId)
        return reminder.toModel()
    }

    override suspend fun getAll(): List<Reminder> {
        val reminders = reminderDao.getAll()
        return reminders.map {
            it.toModel()
        }
    }

    override fun save(reminder: Reminder) {
        val serialized = reminder.toEntity()
        reminderDao.insertAll(serialized)
    }

    override suspend fun upsert(reminder: Reminder) {
        val serialized = reminder.toEntity()
        reminderDao.upsert(serialized)
    }
}