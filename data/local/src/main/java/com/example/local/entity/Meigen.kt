package com.example.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Meigen(
    @PrimaryKey val id: String,
    val body: String,
    val createdAt: Int,
) {
    companion object {
        fun from(model: com.example.model.Meigen): Meigen {
            return Meigen(
                id = model.id,
                body = model.body,
                createdAt = model.createdAt.time.toInt(),
            )
        }
    }
}