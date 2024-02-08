package com.example.reminder.list

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.model.Reminder
import com.example.navigation.Screen

data class ReminderListPageState(
    val reminders: List<Reminder> = emptyList(),
    val isLoading: Boolean = false,
    val onClickListItem: (String) -> Unit,
    val onSwitchChanged: (Reminder, Boolean) -> Unit,
)

@Composable
fun rememberReminderListPageState(
    viewModel: ReminderListPageViewModel,
    navController: NavController,
): ReminderListPageState {
    val isLoading = viewModel.isLoading
    val reminders = viewModel.reminders
    val onSwitchChanged = viewModel::onSwitchChanged

    return ReminderListPageState(
        isLoading = isLoading,
        reminders = reminders,
        onClickListItem = {
            navController.navigate(Screen.ReminderEdit.createRoute(it))
        },
        onSwitchChanged = onSwitchChanged,
    )
}