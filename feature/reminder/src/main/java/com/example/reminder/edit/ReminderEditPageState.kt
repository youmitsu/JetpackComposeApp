package com.example.reminder.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.reminder.common.ReminderFormState
import com.example.reminder.common.rememberReminderFormState

data class ReminderEditPageState(
    val isLoading: Boolean = false,
    val onClickSave: () -> Unit,
    val onClickDelete: () -> Unit,
    val reminderFormState: ReminderFormState,
)

@Composable
fun rememberReminderEditPageState(
    viewModel: ReminderEditPageViewModel,
): ReminderEditPageState {
    val isLoading = viewModel.isLoading
    val title = viewModel.title
    val onTitleChange = viewModel::onTitleChange
    val onClickSave = viewModel::update
    val onClickDelete = viewModel::delete

    val reminderFormState = rememberReminderFormState(title = title, onTitleChange = onTitleChange)

    return remember(
        reminderFormState
    ) {
        ReminderEditPageState(
            isLoading = isLoading,
            onClickSave = onClickSave,
            onClickDelete = onClickDelete,
            reminderFormState = reminderFormState,
        )
    }
}