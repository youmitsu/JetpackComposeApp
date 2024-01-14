package com.example.edit

import com.example.model.Meigen

data class EditUiState(
    val id: String = "",
    val meigen: Meigen? = null,
    val body: String = "",
    val isLoading: Boolean = false,
)
