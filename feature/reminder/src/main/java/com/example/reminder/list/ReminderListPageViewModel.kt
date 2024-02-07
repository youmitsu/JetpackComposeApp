package com.example.reminder.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Reminder
import com.example.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReminderListPageViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository,
) : ViewModel() {

    var reminders = mutableStateListOf<Reminder>()
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun load() {
        isLoading = true
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    reminderRepository.getAll()
                }
                reminders.clear()
                reminders.addAll(data)
            } finally {
                isLoading = false
            }
        }
    }

    fun onSwitchChanged(reminder: Reminder, enabled: Boolean) {
        val targetIndex = reminders.indexOfFirst { it.id == reminder.id }
        if (targetIndex == -1) {
            return
        }
        viewModelScope.launch {
            try {
                val r = reminders[targetIndex]
                val updatedReminder = r.copy(
                    enabled = enabled
                )
                withContext(Dispatchers.IO) {
                    reminderRepository.upsert(updatedReminder)
                }
                reminders[targetIndex] = updatedReminder
            } finally {
                // TODO
            }
        }
    }
}