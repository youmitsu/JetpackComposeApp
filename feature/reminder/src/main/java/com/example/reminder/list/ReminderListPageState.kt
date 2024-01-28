package com.example.reminder.list

import androidx.compose.runtime.Composable
import com.example.model.Reminder

data class ReminderListPageState(
    val reminders: List<Reminder> = emptyList(),
    val isLoading: Boolean = false,
)

@Composable
fun rememberReminderListPageState(
    viewModel: ReminderListPageViewModel,
): ReminderListPageState {
    val isLoading = viewModel.isLoading
    val reminders = viewModel.reminders

    return ReminderListPageState(
        isLoading = isLoading,
        reminders = reminders,
    )
}