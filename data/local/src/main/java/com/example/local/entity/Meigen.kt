package com.example.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "meigens")
data class Meigen(
    @PrimaryKey val id: String,
    val body: String,
    val createdAt: Int,
)

fun com.example.model.Meigen.toEntity(): Meigen {
    return Meigen(
        id = id,
        body = body,
        createdAt = createdAt.time.toInt(),
    )
}

fun Meigen.toModel(): com.example.model.Meigen {
    return com.example.model.Meigen(
        id = id,
        body = body,
        createdAt = Date(this.createdAt.toLong()),
    )
}