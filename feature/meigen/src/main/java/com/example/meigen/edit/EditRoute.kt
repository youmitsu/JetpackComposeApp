package com.example.meigen.edit

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature.list.R
import com.example.model.Meigen
import com.example.ui.component.SaveButton
import com.example.ui.theme.BaseAppTheme
import java.util.Date

@Composable
fun EditRoute(id: String, navController: NavController) {
    EditScreen(id, navController)
}

@Composable
fun EditScreen(
    id: String,
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.load(id)
    }
    LaunchedEffect(Unit) {
        viewModel.onSavedEvent.collect {
            when (it) {
                EditEvent.Saved -> {
                    Toast.makeText(context, R.string.saved_dialog_title, Toast.LENGTH_SHORT).show()
                }

                EditEvent.Deleted -> {
                    Toast.makeText(context, R.string.delete_dialog_title, Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                }
            }
        }
    }
    Edit(
        state = uiState,
        onUpdateBody = viewModel::onUpdateBody,
        onClickNavIcon = { navController.navigateUp() },
        onClickSave = viewModel::save,
        onClickDelete = viewModel::delete,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(
    state: EditUiState,
    onUpdateBody: (String) -> Unit,
    onClickNavIcon: () -> Unit,
    onClickSave: () -> Unit,
    onClickDelete: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onClickNavIcon) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text("名言を編集")
                },
                actions = {
                    IconButton(
                        onClick = onClickDelete
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete"
                        )
                    }
                    SaveButton(
                        isLoading = state.isLoading,
                        onClick = onClickSave
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            TextField(
                value = state.body,
                onValueChange = onUpdateBody,
                modifier = Modifier
                    .fillMaxSize(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        "メモしたい名言を入力",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditPreview() {
    val meigen = Meigen(
        id = "1",
        body = "吾輩は猫である。名前はまだない",
        createdAt = Date()
    )
    BaseAppTheme {
        Edit(
            state = EditUiState(
                id = meigen.id,
                body = meigen.body,
                meigen = meigen,
            ),
            onUpdateBody = {},
            onClickNavIcon = {},
            onClickSave = {},
            onClickDelete = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EditPreviewDark() {
    val meigen = Meigen(
        id = "1",
        body = "吾輩は猫である。名前はまだない",
        createdAt = Date()
    )
    BaseAppTheme {
        Edit(
            state = EditUiState(
                id = meigen.id,
                body = meigen.body,
                meigen = meigen,
            ),
            onUpdateBody = {},
            onClickNavIcon = {},
            onClickSave = {},
            onClickDelete = {}
        )
    }
}