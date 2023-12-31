package com.example.registration.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Meigen
import com.example.repository.MeigenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val meigenRepository: MeigenRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    private val _onSavedEvent = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val onSavedEvent: SharedFlow<Unit> = _onSavedEvent.asSharedFlow()

    fun onUpdateTitle(title: String) {
        _uiState.update { currentState ->
            currentState.copy(
                title = title,
            )
        }
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            try {
                val meigenId = meigenRepository.createId()
                val meigen = Meigen(
                    id = meigenId,
                    body = uiState.value.title,
                    createdAt = Date(),
                )
                meigenRepository.save(meigen)
                _onSavedEvent.emit(Unit)
            } finally {
                _uiState.update { currentState ->
                    currentState.copy(isLoading = false)
                }
            }
        }
    }
}