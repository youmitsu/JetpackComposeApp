package com.example.reminder.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Reminder
import com.example.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReminderEditPageViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository
) : ViewModel() {

    sealed class Event {
        object Updated : Event()
        object Deleted: Event()
    }

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    var isLoading by mutableStateOf(false)
        private set

    var title by mutableStateOf("")
        private set

    var reminder by mutableStateOf<Reminder?>(null)
        private set

    fun load(reminderId: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    reminderRepository.get(reminderId)
                }
                reminder = data
                title = data.title
            } finally {
                isLoading = false
            }
        }
    }

    fun update() {
        val reminder = reminder ?: return
        isLoading = true
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    reminderRepository.upsert(
                        reminder.copy(
                            title = title,
                        )
                    )
                }
                _event.send(Event.Updated)
            } finally {
                isLoading = false
            }
        }
    }

    fun delete() {
        val reminder = reminder ?: return
        isLoading = true
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    reminderRepository.delete(reminder.id)
                }
                _event.send(Event.Deleted)
            } finally {
                isLoading = false
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        title = newTitle
    }
}