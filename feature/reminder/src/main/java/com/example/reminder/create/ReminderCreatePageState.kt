package com.example.reminder.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.reminder.common.ReminderFormState
import com.example.reminder.common.rememberReminderFormState

data class ReminderCreatePageState(
    val isSaving: Boolean,
    val reminderFormState: ReminderFormState,
)

@Composable
fun rememberReminderCreatePageState(
    viewModel: ReminderCreatePageViewModel,
): ReminderCreatePageState {
    val isSaving = viewModel.isSaving
    val title = viewModel.title
    val onTitleChange = viewModel::onTitleChange

    val reminderFormState = rememberReminderFormState(
        title = title,
        onTitleChange = onTitleChange
    )

    return remember(isSaving, title, onTitleChange) {
        ReminderCreatePageState(
            isSaving = isSaving,
            reminderFormState = reminderFormState,
        )
    }
}