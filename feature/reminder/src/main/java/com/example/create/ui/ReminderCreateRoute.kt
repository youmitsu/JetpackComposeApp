package com.example.create.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ReminderCreateRoute() {
    ReminderCreateScreen()
}

@Composable
internal fun ReminderCreateScreen() {
    Column {
        Text("ReminderCreate")
    }
}