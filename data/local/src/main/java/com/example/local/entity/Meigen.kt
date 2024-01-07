package com.example.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meigen(
    @PrimaryKey val id: Int,
    @ColumnInfo val body: String,
) {
    companion object {
        fun from(model: com.example.model.Meigen): Meigen {
            return Meigen(
                id = model.id,
                body = model.body,
            )
        }
    }
}