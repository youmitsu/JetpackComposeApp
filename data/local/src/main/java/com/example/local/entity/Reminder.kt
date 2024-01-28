package com.example.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey val id: String,
    val title: String,
    val enabled: Boolean,
    val createdAt: Int,
)

fun com.example.model.Reminder.toEntity(): Reminder {
    return Reminder(
        id = id,
        title = title,
        enabled = enabled,
        createdAt = createdAt.time.toInt(),
    )
}

fun Reminder.toModel(): com.example.model.Reminder {
    return com.example.model.Reminder(
        id = id,
        title = title,
        enabled = enabled,
        createdAt = Date(this.createdAt.toLong()),
    )
}