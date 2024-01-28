package com.example.reminder.edit

import androidx.compose.runtime.Composable

data class ReminderEditPageState(
    val isLoading: Boolean = false,
    val title: String = "",
    val onTitleChange: (String) -> Unit,
    val onClickSave: () -> Unit,
)

@Composable
fun rememberReminderEditPageState(
    viewModel: ReminderEditPageViewModel,
): ReminderEditPageState {
    val isLoading = viewModel.isLoading
    val title = viewModel.title

    return ReminderEditPageState(
        isLoading = isLoading,
        title = title,
        onTitleChange = viewModel::onTitleChange,
        onClickSave = viewModel::update,
    )
}