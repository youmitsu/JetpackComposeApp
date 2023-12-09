package com.example.registration

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registration.ui.RegistrationUiState
import com.example.registration.ui.RegistrationViewModel
import com.example.ui.theme.BaseAppTheme

@Composable
fun RegistrationRoute() {
    RegistrationScreen()
}

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    RegistrationUiHost(
        state = state.value,
        onUpdateTitle = viewModel::onUpdateTitle
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationUiHost(
    state: RegistrationUiState,
    onUpdateTitle: (value: String) -> Unit,
) {
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
                    value = state.title,
                    onValueChange = { value ->
                        onUpdateTitle(value)
                    },
                    modifier = Modifier.background(Color.Transparent)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationUiPreview() {
    val state = RegistrationUiState()
    BaseAppTheme {
        RegistrationUiHost(state = state, onUpdateTitle = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkRegistrationUiPreview() {
    val state = RegistrationUiState()
    BaseAppTheme {
        RegistrationUiHost(state = state, onUpdateTitle = {})
    }
}