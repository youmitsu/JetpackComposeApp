package com.example.model

import java.util.Date

data class Reminder(
    val id: String,
    val title: String,
    val enabled: Boolean,
    val createdAt: Date,
)
