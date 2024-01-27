package com.example.reminder.create

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
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReminderCreatePageViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository,
) : ViewModel() {

    sealed class Event {
        object Saved : Event()
    }

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    var isSaving by mutableStateOf(false)
        private set

    var title by mutableStateOf("")
        private set

    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    fun save() {
        viewModelScope.launch {
            isSaving = true
            try {
                val reminderId = reminderRepository.createId()
                val reminder = Reminder(
                    id = reminderId,
                    title = title,
                    createdAt = Date(),
                )
                withContext(Dispatchers.IO) {
                    reminderRepository.save(reminder)
                }
                _event.send(Event.Saved)
            } finally {
                isSaving = false
            }
        }
    }
}