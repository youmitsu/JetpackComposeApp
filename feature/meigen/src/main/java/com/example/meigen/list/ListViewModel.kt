package com.example.meigen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.MeigenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val meigenRepository: MeigenRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    private val _onEditClickEvent = Channel<String>(Channel.UNLIMITED)
    val onEditClickEvent = _onEditClickEvent.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meigenList = meigenRepository.getAll()
            _uiState.update { currentState ->
                currentState.copy(currentItems = meigenList)
            }
        }
    }

    fun refresh() {
        _uiState.update { currentState ->
            currentState.copy(
                isRefreshing = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            val meigenList = meigenRepository.getAll()
            _uiState.update { currentState ->
                currentState.copy(currentItems = meigenList, isRefreshing = false)
            }
        }
    }

    fun onClickEdit(id: String) {
        viewModelScope.launch {
            _onEditClickEvent.send(id)
        }
    }
}