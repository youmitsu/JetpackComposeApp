package com.example.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.MeigenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

enum class EditEvent {
    Saved,
    Deleted,
}

@HiltViewModel
class EditViewModel @Inject constructor(
    private val meigenRepository: MeigenRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditUiState())
    val uiState = _uiState.asStateFlow()

    private val _onSavedEvent = Channel<EditEvent>(Channel.UNLIMITED)
    val onSavedEvent = _onSavedEvent.receiveAsFlow()

    fun load(meigenId: String) {
        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            try {
                val meigen = withContext(Dispatchers.IO) {
                    meigenRepository.get(meigenId)
                }
                _uiState.update {
                    it.copy(
                        id = meigenId, // FIXME: viewModelのconstructorでidを初期化したいが、hiltViewModel()を使ってAssistedInjectができない
                        meigen = meigen,
                        body = meigen.body,
                    )
                }
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    fun save() {
        val meigen = _uiState.value.meigen ?: return

        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    meigenRepository.upsert(
                        meigen.copy(
                            body = _uiState.value.body,
                        )
                    )
                }
                _onSavedEvent.send(EditEvent.Saved)
            } finally {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            }
        }
    }

    fun delete() {
        val meigen = _uiState.value.meigen ?: return

        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    meigenRepository.delete(meigen.id)
                }
                _onSavedEvent.send(EditEvent.Deleted)
            } finally {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            }
        }
    }

    fun onUpdateBody(body: String) {
        _uiState.update {
            it.copy(
                body = body,
            )
        }
    }
}
