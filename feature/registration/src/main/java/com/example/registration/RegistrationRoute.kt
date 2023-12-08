package com.example.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registration.ui.RegistrationViewModel

@Composable
fun RegistrationRoute() {
    RegistrationScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("名言を登録")
            })
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Column {
                TextField(
                    value = state.value.title,
                    onValueChange = {value ->
                        viewModel.onUpdateTitle(value)
                    },
                )
            }
        }
    }
}