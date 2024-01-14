package com.example.edit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.common.SaveButton
import com.example.ui.theme.BaseAppTheme

@Composable
fun EditRoute(navController: NavController) {
    EditScreen(navController)
}

@Composable
fun EditScreen(
    navController: NavController,
) {
    Edit(
        isLoading = false,
        onClickNavIcon = { navController.popBackStack() },
        onClickSave = {},
        onClickDelete = {}
    )
}

@Composable
fun Edit(
    isLoading: Boolean,
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
                    androidx.compose.material3.Text("名言を編集")
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
                        isLoading = isLoading,
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

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditPreview() {
    BaseAppTheme {
        Edit(
            isLoading = false,
            onClickNavIcon = {},
            onClickSave = {},
            onClickDelete = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EditPreviewDark() {
    BaseAppTheme {
        Edit(
            isLoading = false,
            onClickNavIcon = {},
            onClickSave = {},
            onClickDelete = {}
        )
    }
}