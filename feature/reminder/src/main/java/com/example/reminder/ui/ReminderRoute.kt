package com.example.reminder.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReminderRoute() {
    ReminderScreen()
}

@Composable
internal fun ReminderScreen(
    modifier: Modifier = Modifier
) {
    Column {
        Text("ReminderSetting")
    }
}