package com.example.reminder.create

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reminder.R
import com.example.reminder.common.ReminderForm
import com.example.reminder.common.ReminderFormState
import com.example.ui.component.SaveButton
import com.example.ui.theme.BaseAppTheme

@Composable
fun ReminderCreatePageHost(
    navController: NavController,
    viewModel: ReminderCreatePageViewModel = hiltViewModel(),
) {
    val pageState = rememberReminderCreatePageState(
        viewModel = viewModel,
    )
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                ReminderCreatePageViewModel.Event.Saved -> {
                    Toast.makeText(context, R.string.reminder_saved_message, Toast.LENGTH_SHORT)
                        .show()
                    navController.navigateUp()
                }
            }
        }
    }

    ReminderCreatePage(
        pageState = pageState,
        onClickNavIcon = { navController.navigateUp() },
        onClickSave = viewModel::save,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReminderCreatePage(
    pageState: ReminderCreatePageState,
    onClickNavIcon: () -> Unit,
    onClickSave: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.reminder_create_app_bar_title))
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
                        onClick = onClickSave
                    )
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ReminderForm(
                state = pageState.reminderFormState,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ReminderCreatePreview() {
    BaseAppTheme {
        ReminderCreatePage(
            pageState = ReminderCreatePageState(
                isSaving = false,
                reminderFormState = ReminderFormState(
                    title = "title",
                    onTitleChange = {},
                )
            ),
            onClickNavIcon = {},
            onClickSave = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReminderCreatePreviewDark() {
    BaseAppTheme {
        ReminderCreatePage(
            pageState = ReminderCreatePageState(
                isSaving = false,
                reminderFormState = ReminderFormState(
                    title = "title",
                    onTitleChange = {},
                )
            ),
            onClickNavIcon = {},
            onClickSave = {},
        )
    }
}
