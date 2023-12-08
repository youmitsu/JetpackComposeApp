package com.example.registration.ui

import androidx.lifecycle.ViewModel
import com.example.repository.MeigenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val meigenRepository: MeigenRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    fun onUpdateTitle(title: String) {
        _uiState.update { currentState ->
            currentState.copy(
                title = title,
            )
        }
    }
}