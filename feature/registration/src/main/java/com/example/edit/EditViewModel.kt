package com.example.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.MeigenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val meigenRepository: MeigenRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditUiState())
    val uiState = _uiState.asStateFlow()

    fun load(meigenId: String) {
        _uiState.update {
            it.copy(
                id = meigenId, // FIXME: viewModelのconstructorでidを初期化したいが、hiltViewModel()を使ってAssistedInjectができない
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

    }

    companion object {
        const val KEY_MEIGEN_ID = "meigen_id"
    }
}
