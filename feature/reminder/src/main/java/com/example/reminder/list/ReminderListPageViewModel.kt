package com.example.reminder.list

import androidx.compose.runtime.getValue
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

    var reminders by mutableStateOf(emptyList<Reminder>())
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
                reminders = data
            } finally {
                isLoading = false
            }
        }
    }
}