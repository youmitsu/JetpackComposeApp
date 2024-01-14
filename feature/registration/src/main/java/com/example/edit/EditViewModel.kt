package com.example.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.MeigenRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel(assistedFactory = EditViewModelFactory::class)
class EditViewModel @AssistedInject constructor(
    @Assisted private val meigenId: String,
    private val meigenRepository: MeigenRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditUiState())
    val uiState = _uiState.asStateFlow()

    fun load() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            try {
                val meigen = withContext(Dispatchers.IO) {
                    meigenRepository.get(meigenId)
                }
                _uiState.update {
                    it.copy(
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
}

@AssistedFactory
interface EditViewModelFactory {
    fun create(meigenId: String): EditViewModel
}