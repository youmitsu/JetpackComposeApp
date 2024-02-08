package com.example.reminder.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reminder.R
import com.example.ui.theme.BaseAppTheme

data class ReminderFormState(
    // title
    val title: String,
    val onTitleChange: (String) -> Unit,
)

@Composable
fun rememberReminderFormState(
    title: String,
    onTitleChange: (String) -> Unit,
): ReminderFormState {
    return remember(
        title,
        onTitleChange,
    ) {
        ReminderFormState(
            title = title,
            onTitleChange = onTitleChange,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderForm(
    state: ReminderFormState,
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(stringResource(id = R.string.reminder_form_title))
        TextField(
            value = state.title,
            onValueChange = state.onTitleChange,
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
//                Text(stringResource(id = R.string.reminder_form_notification_target))
//                ListItem(
//                    headlineText = {
//                        Text(stringResource(id = R.string.reminder_form_notification_target_all))
//                    },
//                    trailingContent = {
//                        Icon(Icons.Filled.ArrowRight, contentDescription = "")
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .border(
//                            width = 1.dp,
//                            color = Color.Gray,
//                        )
//                        .clickable {
//                            // TODO:
//                        },
//                )
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderFormPreview() {
    BaseAppTheme {
        ReminderForm(
            state = ReminderFormState(
                title = "title",
                onTitleChange = {},
            )
        )
    }
}