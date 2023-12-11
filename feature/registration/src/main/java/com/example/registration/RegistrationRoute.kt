package com.example.registration

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature.registration.R
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
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.onSavedEvent) {
        viewModel.onSavedEvent.collect {
            Toast.makeText(context, R.string.saved_dialog_title, Toast.LENGTH_SHORT).show()
        }
    }

    Registration(
        state = state.value,
        onUpdateTitle = viewModel::onUpdateTitle,
        onClickSave = viewModel::save,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(
    state: RegistrationUiState,
    onUpdateTitle: (value: String) -> Unit,
    onClickSave: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("名言を登録")
                },
                actions = {
                    SaveButton(
                        isLoading = state.isLoading,
                        onClick = onClickSave
                    )
                },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            TextField(
                value = state.title,
                onValueChange = { value ->
                    onUpdateTitle(value)
                },
                modifier = Modifier
                    .fillMaxSize(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                ),
                placeholder = {
                    Text("メモしたい名言を入力", color = MaterialTheme.colorScheme.secondary)
                }
            )
        }
    }
}

@Composable
fun SaveButton(isLoading: Boolean, onClick: () -> Unit) {
    if (isLoading) {
        return CircularProgressIndicator(
            modifier = Modifier.padding(end = 16.dp)
        )
    }
    Button(onClick = onClick) {
        Icon(
            Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.save)
        )
    }
}

@Composable
fun SavedDialog() {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(375.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.saved_dialog_title))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationUiPreview() {
    val state = RegistrationUiState()
    BaseAppTheme {
        Registration(state = state, onUpdateTitle = {}, onClickSave = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkRegistrationUiPreview() {
    val state = RegistrationUiState()
    BaseAppTheme {
        Registration(state = state, onUpdateTitle = {}, onClickSave = {})
    }
}