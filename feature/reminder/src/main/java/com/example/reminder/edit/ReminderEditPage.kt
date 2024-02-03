package com.example.reminder.edit

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reminder.R
import com.example.reminder.common.ReminderForm
import com.example.ui.component.SaveButton
import com.example.ui.theme.BaseAppTheme

@Composable
fun ReminderEditPageHost(
    navController: NavController,
    reminderId: String,
    viewModel: ReminderEditPageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val pageState = rememberReminderEditPageState(
        viewModel = viewModel,
    )
    LaunchedEffect(Unit) {
        viewModel.load(reminderId)
    }
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                ReminderEditPageViewModel.Event.Updated -> {
                    Toast.makeText(context, R.string.reminder_updated_message, Toast.LENGTH_SHORT)
                        .show()
                    navController.navigateUp()
                }
                ReminderEditPageViewModel.Event.Deleted -> {
                    Toast.makeText(context, R.string.reminder_deleted_message, Toast.LENGTH_SHORT)
                        .show()
                    navController.navigateUp()
                }
            }
        }
    }
    ReminderEditPage(
        pageState = pageState,
        onClickNavIcon = { navController.navigateUp() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderEditPage(
    pageState: ReminderEditPageState,
    onClickNavIcon: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.reminder_edit_app_bar_title))
                },
                navigationIcon = {
                    IconButton(onClick = onClickNavIcon) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    SaveButton(
                        isLoading = false,
                        onClick = pageState.onClickSave
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
            ReminderForm(
                title = pageState.title,
                onTitleChange = pageState.onTitleChange,
            )
            TextButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                onClick = pageState.onClickDelete,
            ) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.error
                )
                Text(
                    stringResource(id = R.string.reminder_form_delete_title),
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderEditPagePreview() {
    BaseAppTheme {
        ReminderEditPage(
            pageState = ReminderEditPageState(
                isLoading = false,
                title = "title",
                onTitleChange = {},
                onClickSave = {},
                onClickDelete = {},
            ),
            onClickNavIcon = {}
        )
    }
}