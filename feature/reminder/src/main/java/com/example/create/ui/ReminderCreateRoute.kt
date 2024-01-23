package com.example.create.ui

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature.reminder.R
import com.example.ui.component.SaveButton
import com.example.ui.theme.BaseAppTheme

@Composable
fun ReminderCreateRoute(navController: NavController) {
    ReminderCreateScreen(
        navController = navController
    )
}

@Composable
internal fun ReminderCreateScreen(
    navController: NavController,
) {
    ReminderCreate(
        onClickNavIcon = { navController.navigateUp() },
        onClickSave = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReminderCreate(
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
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(stringResource(id = R.string.reminder_form_title))
                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(16.dp))
                Text(stringResource(id = R.string.reminder_form_notification_frequency))
                ListItem(
                    headlineText = {
                        Text("毎日10時")
                    },
                    trailingContent = {
                        Icon(Icons.Filled.ArrowRight, contentDescription = "")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                        )
                        .clickable {
                            // TODO:
                        },
                )
                Spacer(Modifier.height(16.dp))
                Text(stringResource(id = R.string.reminder_form_notification_target))
                ListItem(
                    headlineText = {
                        Text("名言A")
                    },
                    trailingContent = {
                        Icon(Icons.Filled.ArrowRight, contentDescription = "")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                        )
                        .clickable {
                            // TODO:
                        },
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ReminderCreatePreview() {
    BaseAppTheme {
        ReminderCreate(
            onClickNavIcon = {},
            onClickSave = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReminderCreatePreviewDark() {
    BaseAppTheme {
        ReminderCreate(
            onClickNavIcon = {},
            onClickSave = {},
        )
    }
}
