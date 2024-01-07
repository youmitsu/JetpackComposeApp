package com.example.feature.list.ui

import com.example.model.Meigen

data class ListUiState(
    val currentItems: List<Meigen> = listOf(),
    val isRefreshing: Boolean = false,
)