package com.example.create.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class ReminderCreatePageState(
    val isSaving: Boolean,
    val title: String,
    val onTitleChange: (String) -> Unit,
)

@Composable
fun rememberReminderCreatePageState(
    viewModel: ReminderCreatePageViewModel,
): ReminderCreatePageState {
    val isSaving = viewModel.isSaving
    val title = viewModel.title
    val onTitleChange = viewModel::onTitleChange

    return remember(isSaving, title, onTitleChange) {
        ReminderCreatePageState(
            isSaving = isSaving,
            title = title,
            onTitleChange = onTitleChange,
        )
    }
}