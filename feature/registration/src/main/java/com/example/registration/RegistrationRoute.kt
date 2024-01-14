package com.example.registration

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.SaveButton
import com.example.feature.registration.R
import com.example.registration.ui.RegistrationUiState
import com.example.registration.ui.RegistrationViewModel
import com.example.ui.theme.BaseAppTheme

@Composable
fun RegistrationRoute(navController: NavController) {
    RegistrationScreen(navController)
}

@Composable
fun RegistrationScreen(
    navController: NavController,
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
        navController = navController,
        state = state.value,
        onUpdateTitle = viewModel::onUpdateTitle,
        onClickSave = viewModel::save,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(
    navController: NavController,
    state: RegistrationUiState,
    onUpdateTitle: (value: String) -> Unit,
    onClickSave: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
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

@Preview(showBackground = true)
@Composable
fun RegistrationUiPreview() {
    val navController = rememberNavController()
    val state = RegistrationUiState()
    BaseAppTheme {
        Registration(
            navController = navController,
            state = state,
            onUpdateTitle = {},
            onClickSave = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkRegistrationUiPreview() {
    val navController = rememberNavController()
    val state = RegistrationUiState()
    BaseAppTheme {
        Registration(
            navController = navController,
            state = state,
            onUpdateTitle = {},
            onClickSave = {})
    }
}