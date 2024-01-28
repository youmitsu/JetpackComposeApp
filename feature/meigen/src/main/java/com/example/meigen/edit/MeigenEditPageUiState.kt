package com.example.meigen.edit

import com.example.model.Meigen

data class MeigenEditPageUiState(
    val id: String = "",
    val meigen: Meigen? = null,
    val body: String = "",
    val isLoading: Boolean = false,
)
