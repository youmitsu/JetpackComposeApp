package com.example.reminder.edit

import androidx.compose.runtime.Composable

data class ReminderEditPageState(
    val isLoading: Boolean = false,
    val title: String = "",
    val onTitleChange: (String) -> Unit,
    val onClickSave: () -> Unit,
    val onClickDelete: () -> Unit,
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

    return ReminderEditPageState(
        isLoading = isLoading,
        title = title,
        onTitleChange = onTitleChange,
        onClickSave = onClickSave,
        onClickDelete = onClickDelete,
    )
}