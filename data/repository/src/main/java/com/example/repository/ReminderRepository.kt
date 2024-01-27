package com.example.repository

import com.example.local.dao.ReminderDao
import com.example.local.entity.toEntity
import com.example.model.Reminder
import java.util.UUID
import javax.inject.Inject

interface ReminderRepository {
    fun createId(): String
    fun save(title: Reminder)
}

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao,
) : ReminderRepository {
    override fun createId(): String {
        return UUID.randomUUID().toString()
    }

    override fun save(reminder: Reminder) {
        val serialized = reminder.toEntity()
        reminderDao.insertAll(serialized)
    }
}